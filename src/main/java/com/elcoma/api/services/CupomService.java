package com.elcoma.api.services;

import com.elcoma.api.entity.Cupom;
import com.elcoma.api.entity.UsuarioEntity;
import com.elcoma.api.dto.CupomDTO;
import com.elcoma.api.repositories.CupomRepository;
import com.elcoma.api.repositories.UsuarioRepository;
import com.elcoma.api.services.exceptions.DataIntegretyException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Cupom insert(Cupom cupom) {
        cupom.setId(null);
        cupom = repository.save(cupom);
        //sendCuponsForUsuarios(cupom.getId(), idPerfil);
        return cupom;
    }

    public Cupom findById(Integer id) {
        Optional<Cupom> cupom = repository.findById(id);
        return cupom.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: " + id + ", Tipo: " + Cupom.class.getName()
        ));
    }

    public Object update(Cupom cupom) {
        findById(cupom.getId());
        return repository.save(cupom);
    }

    public List<Cupom> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegretyException("Não é possível excluir esse cupom");
        }
    }

    public List<Cupom> findAllByMonthAndUser(String mes, Integer id_usuario) {
        usuarioService.findById(id_usuario);
        Integer ano = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).getYear();
        List<Cupom> cupom = repository.findAllByMothAndUsuario(ano, mes, id_usuario);
        return cupom;
    }

    public void updateStatus(Integer idCupom, Integer idUsuario) {
        findById(idCupom);
        String status = "U";
        repository.updateStatus(idCupom, idUsuario, status);
    }

    public void sendCuponsForUsuarios(Integer idCupom, Integer idPerfil) {
        List<UsuarioEntity> usuarioEntityList = usuarioRepository.findAllByPerfil(idPerfil);
        for (UsuarioEntity usuarioEntity : usuarioEntityList) {
            repository.sendCuponsForUsuarios(idCupom, usuarioEntity.getId());
        }
    }

    public List<CupomDTO> findAllByLojaAndUsuario(String nomeLoja, Integer idUsuario) {
        if (!nomeLoja.equals("")) {
            nomeLoja = nomeLoja + '%';
        }
        List<Cupom> cupomList = repository.findAllByLojaAndUsuario(nomeLoja, idUsuario);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList) {
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getCodigo(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return cupomDTOList;
    }

    public List<CupomDTO> findAllByCategoriaAndUsuario(Integer idCategoria, Integer idUsuario) {
        List<Cupom> cupomList = repository.findAllByCategoriaAndUsuario(idCategoria, idUsuario);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList) {
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getCodigo(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return cupomDTOList;
    }

    public List<CupomDTO> findAllByUsuario(Integer id) {
        List<Cupom> cupomList = repository.findAllByUsuario(id);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList) {
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getCodigo(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return cupomDTOList;
    }

    public List<CupomDTO> findAllByMonthAndYear(String mes, String ano) {
        List<Cupom> cupomList = repository.findAllByMonthAndYear(mes, ano);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList) {
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getCodigo(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return cupomDTOList;
    }

    public List<CupomDTO> findAllByUserAndLojaAndMonthAndLoja(Integer idUsuario, Integer idLoja,
                                                              Integer mes, Integer ano) {
        List<CupomDTO> dtoList = findAllByUsuario(idUsuario);
        dtoList = dtoList.stream()
                .filter(dto -> dto.getIdLoja().equals(idLoja))
                .filter(dto -> dto.getDataInicial().getMonthValue() == mes)
                .filter(dto -> dto.getDataInicial().getYear() == ano)
                .collect(Collectors.toList());
        return dtoList;
    }
}
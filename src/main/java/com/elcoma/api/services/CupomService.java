package com.elcoma.api.services;
import com.elcoma.api.domain.Cupom;
import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.Usuario;
import com.elcoma.api.dto.CupomDTO;
import com.elcoma.api.repositories.CupomRepository;
import com.elcoma.api.repositories.UsuarioRepository;
import com.elcoma.api.services.exceptions.DataIntegretyException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Cupom insert(Cupom cupom, Integer idPerfil){
        cupom.setId(null);
        cupom = repository.save(cupom);
        sendCuponsForUsuarios(cupom.getId(), idPerfil);
        return cupom;
    }

    public Cupom findById(Integer id){
        Optional<Cupom> cupom = repository.findById(id);
        return cupom.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: "+id+", Tipo: "+ Cupom.class.getName()
        ));
    }

    public Object update(Cupom cupom) {
        findById(cupom.getId());
        return repository.save(cupom);
    }

    public List<Cupom> findAll(){return repository.findAll();}

    public void delete(Integer id){
        findById(id);
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegretyException("Não é possível excluir esse cupom");
        }
    }

    public List<Cupom> findAllByMonthAndUser(String mes, Integer id_usuario){
        usuarioService.findById(id_usuario);
        Integer ano =  LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).getYear();
        List<Cupom> cupom = repository.findAllByMothAndUsuario(ano, mes, id_usuario);
        return cupom;
    }
    public void updateStatus(Integer idCupom, Integer idUsuario) {
        findById(idCupom);
        String status = "U";
        repository.updateStatus(idCupom, idUsuario, status);
    }

    public void sendCuponsForUsuarios(Integer idCupom, Integer idPerfil){
        List<Usuario> usuarioList = usuarioRepository.findAllByPerfil(idPerfil);
        for (Usuario usuario: usuarioList ){
            repository.sendCuponsForUsuarios(idCupom, usuario.getId());
        }
    }

    public List<CupomDTO> findAllByLojaAndUsuario(String nomeLoja, Integer idUsuario) {
        if(!nomeLoja.equals("")){
            nomeLoja = nomeLoja+'%';
        }

        List<Cupom> cupomList = repository.findAllByLojaAndUsuario(nomeLoja, idUsuario);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList){
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return  cupomDTOList;
    }

    public List<CupomDTO> findAllByCategoriaAndUsuario(Integer idCategoria, Integer idUsuario) {
        List<Cupom> cupomList = repository.findAllByCategoriaAndUsuario(idCategoria, idUsuario);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList){
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return  cupomDTOList;
    }


    public List<CupomDTO> findAllByUsuario(Integer id) {
        List<Cupom> cupomList = repository.findAllByUsuario(id);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList){
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return  cupomDTOList;
    }

    public List<CupomDTO> findAllByMothAndYear(String mes, String ano) {
        List<Cupom> cupomList = repository.findAllByMothAndYear(mes, ano);
        List<CupomDTO> cupomDTOList = new ArrayList<>();
        for (Cupom cupom : cupomList){
            CupomDTO cupomDTO = new CupomDTO(
                    cupom.getId(),
                    cupom.getTitulo(),
                    cupom.getDescricao(),
                    cupom.getDataInicial(),
                    cupom.getValidade(),
                    cupom.getValor(),
                    cupom.getLoja().getId(),
                    cupom.getLoja().getNome()
            );
            cupomDTOList.add(cupomDTO);
        }
        return  cupomDTOList;
    }
}

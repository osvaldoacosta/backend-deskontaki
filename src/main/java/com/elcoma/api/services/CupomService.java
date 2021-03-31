package com.elcoma.api.services;

import com.elcoma.api.domain.Cupom;
import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.Usuario;
import com.elcoma.api.repositories.CupomRepository;

import com.elcoma.api.services.exceptions.DataIntegretyException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;

    @Transactional
    public Cupom insert(Cupom cupom){
        cupom.setId(null);
        cupom = repository.save(cupom);
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

    public List<Cupom> findAllByMothAndUsuario(String mes, Integer id_usuario){
        findById(id_usuario);
        List<Cupom> cupom = repository.findAllByMothAndUsuario(mes, id_usuario);
        return cupom;
    }
}

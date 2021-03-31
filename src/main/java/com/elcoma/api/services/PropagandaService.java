package com.elcoma.api.services;

import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.Propaganda;
import com.elcoma.api.repositories.PropagandaRepository;
import com.elcoma.api.services.exceptions.DataIntegretyException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PropagandaService {

    @Autowired
    private PropagandaRepository repository;

    public Propaganda insert(Propaganda propaganda){
        propaganda.setId(null);
        propaganda = repository.save(propaganda);
        return propaganda;
    }

    @Transactional
    public Propaganda findById(Integer id){

        Optional<Propaganda> propaganda = repository.findById(id);

        return propaganda.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: "+id+", Tipo: "+ Propaganda.class.getName()
        ));
    }

    public List<Propaganda> findAll(){return repository.findAll();}

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegretyException("Não é possível excluir essa propaganda, pois ela não existe");
        }
    }
    public Propaganda update(Propaganda propaganda) {
        findById(propaganda.getId());
        return repository.save(propaganda);

    }
}

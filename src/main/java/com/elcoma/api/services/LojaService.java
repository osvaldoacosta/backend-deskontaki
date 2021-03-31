package com.elcoma.api.services;


import com.elcoma.api.domain.Loja;
import com.elcoma.api.repositories.LojaRepository;
import com.elcoma.api.services.exceptions.DataIntegretyException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import com.elcoma.api.domain.Cupom;
import com.elcoma.api.domain.Loja;
import com.elcoma.api.repositories.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LojaService {


   


    @Autowired
    private LojaRepository repository;
    // Cadastrando a loja

    public Loja insert(Loja loja){
        loja.setId(null);
        loja = repository.save(loja);
        return loja;
    }

    @Transactional
    public Loja findById(Integer id){

        Optional<Loja> loja = repository.findById(id);

        return loja.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: "+id+", Tipo: "+ Loja.class.getName()
        ));
     }
  
    public List<Loja> findAll(){return repository.findAll();}
  
    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegretyException("Não é possível excluir uma Loja que possui notas fiscais cadastradas");
        }
    }
    public Loja update(Loja loja) {
        findById(loja.getId());
        return repository.save(loja);  
                 
    }
}

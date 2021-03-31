package com.elcoma.api.services;

import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.NotaFiscal;
import com.elcoma.api.repositories.NotaFiscalRepository;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository repository;

    @Transactional
    public NotaFiscal insert(NotaFiscal notaFiscal){
        notaFiscal.setId(null);
        notaFiscal = repository.save(notaFiscal);
        return notaFiscal;
    }

    public NotaFiscal findById(Integer id){
        Optional<NotaFiscal> notaFiscal = repository.findById(id);

        return notaFiscal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: "+ id + ", Tipo: " + NotaFiscal.class.getName()
        ));
    }


    public NotaFiscal update(NotaFiscal notaFiscal) {
        findById(notaFiscal.getId());
        return repository.save(notaFiscal);

    }

    public void delete(Integer id){
        findById(id);
        repository.deleteById(id);
    }

    public List<NotaFiscal> findAll(){
        return  repository.findAll();
    }
}

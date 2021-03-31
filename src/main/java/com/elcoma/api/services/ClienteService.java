package com.elcoma.api.services;

import java.util.List;
import java.util.Optional;

import com.elcoma.api.domain.Cliente;
import com.elcoma.api.repositories.ClienteRepository;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService
{
    @Autowired
    private ClienteRepository repository;

    public Cliente insert(Cliente cliente){
        cliente.setId(null);
        cliente = repository.save(cliente);
        return cliente;
    }

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException( 
            "Objeto não encontrado: Id: "+ id +", Tipo: "+ Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente update(Cliente cliente) {
        findById(cliente.getId());
        return repository.save(cliente);
    }
}

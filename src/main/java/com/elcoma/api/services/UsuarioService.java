package com.elcoma.api.services;
import com.elcoma.api.domain.Usuario;
import com.elcoma.api.repositories.UsuarioRepository;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario insert(Usuario usuario){
        usuario.setId(null);
        usuario = repository.save(usuario);
        return usuario;
    }

    public Usuario findById(Integer id){
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario update(Usuario usuario, String cpf) {
        findByCpf(cpf);
        return repository.save(usuario);
    }

    public Usuario findByCpf(String cpf) {
        Optional<Usuario> usuario = repository.findByCpf(cpf);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: CPF: " + cpf + ", Tipo: " + Usuario.class.getName()));
    }

}


package com.elcoma.api.services;

import com.elcoma.api.domain.PerfilUsuario;
import com.elcoma.api.repositories.PerfilUsuarioRepository;
import com.elcoma.api.services.exceptions.DataIntegretyException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository repository;

    @Transactional
    public PerfilUsuario findById(Integer id) {
        Optional<PerfilUsuario> perfilUsuario = repository.findById(id);

        return perfilUsuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: "+id+", Tipo: "+ PerfilUsuario.class.getName()
        ));
    }

    @Transactional
    public PerfilUsuario insert(PerfilUsuario perfilUsuario){
        perfilUsuario.setId(null);
        perfilUsuario = repository.save(perfilUsuario);
        return perfilUsuario;
    }

    public List<PerfilUsuario> findAll(){return repository.findAll();}

    public void delete(Integer id){
        findById(id);
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegretyException("Esse perfil de usuário não foi encontrado");
        }
    }

    public PerfilUsuario update(PerfilUsuario perfilUsuario) {
        findById(perfilUsuario.getId());
        return repository.save(perfilUsuario);
    }
}

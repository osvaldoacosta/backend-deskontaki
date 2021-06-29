package com.elcoma.api.services;
import com.elcoma.api.entity.UsuarioEntity;
import com.elcoma.api.dto.UsuarioDTO;
import com.elcoma.api.repositories.UsuarioRepository;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioEntity insert(UsuarioEntity usuarioEntity){
        usuarioEntity.setId(null);
        usuarioEntity = repository.save(usuarioEntity);
        return usuarioEntity;
    }

    public UsuarioEntity findById(Integer id){
        Optional<UsuarioEntity> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: " + id + ", Tipo: " + UsuarioEntity.class.getName()));
    }

    public List<UsuarioDTO> findAll(){
        List<UsuarioEntity> usuarioEntityList = repository.findAll();
        List<UsuarioDTO> listDto = new ArrayList<>();
        for(UsuarioEntity usuarioEntity : usuarioEntityList){
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuarioEntity.getCpf(),
                    usuarioEntity.getNome(),
                    usuarioEntity.getNascimento(),
                    usuarioEntity.getSexo());
            listDto.add(usuarioDTO);
        }
        return listDto;
    }

    public UsuarioEntity update(UsuarioEntity usuarioEntity, Integer id) {
        UsuarioEntity oldUsuarioEntity = findById(id);
        usuarioEntity.setId(oldUsuarioEntity.getId());
        if(usuarioEntity.getSenha() == null){
            usuarioEntity.setSenha(oldUsuarioEntity.getSenha());
        }
        if(usuarioEntity.getCpf()== null){
            usuarioEntity.setCpf(oldUsuarioEntity.getCpf());
        }
        return repository.save(usuarioEntity);
    }
    public UsuarioEntity findByCpf(String cpf) {
        Optional<UsuarioEntity> usuario = repository.findByCpf(cpf);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: CPF: " + cpf + ", Tipo: " + UsuarioEntity.class.getName()));
    }

}


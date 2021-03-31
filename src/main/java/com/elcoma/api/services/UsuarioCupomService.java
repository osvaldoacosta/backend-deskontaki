package com.elcoma.api.services;

import com.elcoma.api.domain.UsuarioCupom;
import com.elcoma.api.repositories.UsuarioCupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioCupomService {

    @Autowired
    private UsuarioCupomRepository repository;

    public List<UsuarioCupom> findAllByMothAndUsuario(Integer id, String mes){
        List<UsuarioCupom> usuarioCupomList = repository.findAllByMothAndUsuario(mes,  id);
        return usuarioCupomList;
    }
}

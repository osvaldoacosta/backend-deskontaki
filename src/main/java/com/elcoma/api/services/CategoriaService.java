package com.elcoma.api.services;

import com.elcoma.api.domain.Categoria;
import com.elcoma.api.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        categoria = repository.save(categoria);
        return categoria;
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }
}

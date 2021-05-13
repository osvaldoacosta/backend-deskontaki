package com.elcoma.api.resources;

import com.elcoma.api.domain.Categoria;
import com.elcoma.api.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Categoria categoria){
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> categoriaList = service.findAll();
        return ResponseEntity.ok().body(categoriaList);
    }
}

package com.elcoma.api.resources;

import com.elcoma.api.domain.PerfilUsuario;
import com.elcoma.api.services.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/perfilusuario")
public class PerfilUsuarioResource {

    @Autowired
    private PerfilUsuarioService service;

    //get por id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PerfilUsuario> find(@PathVariable Integer id){
        PerfilUsuario perfilUsuario = service.findById(id);
        return ResponseEntity.ok().body(perfilUsuario);
    }

    //get all
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PerfilUsuario>> findAll(){
        List<PerfilUsuario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //post
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody PerfilUsuario perfilUsuario){
        perfilUsuario = service.insert(perfilUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(perfilUsuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody PerfilUsuario perfilUsuario, @PathVariable Integer id){
        perfilUsuario.setId(id);
        perfilUsuario = service.update(perfilUsuario);
        return ResponseEntity.noContent().build();
    }

    //delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}

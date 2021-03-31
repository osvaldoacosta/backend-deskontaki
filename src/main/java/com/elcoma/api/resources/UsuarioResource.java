package com.elcoma.api.resources;

import com.elcoma.api.domain.Usuario;
import com.elcoma.api.repositories.UsuarioRepository;
import com.elcoma.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Usuario usuario) {
        usuario = service.insert(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> findByCpf(@PathVariable String cpf) {
        Usuario usuario = service.findByCpf(cpf);
        return ResponseEntity.ok().body(usuario);
    }

   /* @RequestMapping(value = "/economia", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Double>> economia(){

        return Collections.singletonMap("economia", 1000.50);
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //att pelo cpf
    @RequestMapping(value = "/{cpf}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable String cpf) {
        usuario = service.update(usuario, cpf);
        return ResponseEntity.noContent().build();
    }




}


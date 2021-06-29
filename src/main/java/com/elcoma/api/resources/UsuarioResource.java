package com.elcoma.api.resources;

import com.elcoma.api.entity.UsuarioEntity;
import com.elcoma.api.dto.UsuarioDTO;
import com.elcoma.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> insert(@Valid @RequestBody UsuarioEntity usuarioEntity) {
        usuarioEntity = service.insert(usuarioEntity);
        return ResponseEntity.ok().body(usuarioEntity.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioEntity> find(@PathVariable Integer id) {
        UsuarioEntity usuarioEntity = service.findById(id);
        return ResponseEntity.ok().body(usuarioEntity);
    }

    @RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioEntity> findByCpf(@PathVariable String cpf) {
        UsuarioEntity usuarioEntity = service.findByCpf(cpf);
        return ResponseEntity.ok().body(usuarioEntity);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UsuarioEntity usuarioEntity, @PathVariable Integer id) {
        usuarioEntity = service.update(usuarioEntity, id);
        return ResponseEntity.noContent().build();
    }
}


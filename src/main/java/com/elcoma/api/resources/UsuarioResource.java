package com.elcoma.api.resources;

import com.elcoma.api.domain.Usuario;
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
    public ResponseEntity<Integer> insert(@Valid @RequestBody Usuario usuario) {
        usuario = service.insert(usuario);
        return ResponseEntity.ok().body(usuario.getId());
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
        usuario = service.update(usuario, id);
        return ResponseEntity.noContent().build();
    }
}


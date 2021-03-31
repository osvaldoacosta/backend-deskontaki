package com.elcoma.api.resources;

import com.elcoma.api.domain.Cupom;
import com.elcoma.api.domain.UsuarioCupom;
import com.elcoma.api.services.UsuarioCupomService;
import com.elcoma.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/usuariocupom")
public class UsuarioCupomResource {

    @Autowired
    private UsuarioCupomService service;

    @RequestMapping(value = "/{id}/{mes}", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioCupom>> findAllByMothAndUsuario(@PathVariable Integer id, @PathVariable String mes){
        List<UsuarioCupom> list = service.findAllByMothAndUsuario(id, mes);
        return  ResponseEntity.ok().body(list);
    }
}

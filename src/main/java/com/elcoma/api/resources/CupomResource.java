package com.elcoma.api.resources;

import com.elcoma.api.domain.Cupom;
import com.elcoma.api.services.CupomService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cupons")
public class CupomResource {

    @Autowired
    private CupomService service;

    //metodo post
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Cupom cupom) {
        cupom = service.insert(cupom);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cupom.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    //get by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws ObjectNotFoundException {
        Cupom obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    //get all
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cupom>> findAll() {
        List<Cupom> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //metodo put
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Cupom cupom, @PathVariable Integer id) {
        cupom.setId(id);
        service.update(cupom);

        return ResponseEntity.noContent().build();
    }
    //delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/{mes}", method = RequestMethod.GET)
    public ResponseEntity<List<Cupom>> findAllByMothAndUsuario(@PathVariable Integer id, @PathVariable String mes){
        List<Cupom> list = service.findAllByMothAndUsuario(mes, id);
        return  ResponseEntity.ok().body(list);
    }
}


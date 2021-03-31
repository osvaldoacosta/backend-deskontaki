package com.elcoma.api.resources;

import com.elcoma.api.domain.Loja;
import com.elcoma.api.services.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/lojas")
public class LojaResource {
    
    @Autowired
    private LojaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Loja> find(@PathVariable Integer id){
        Loja loja = service.findById(id);
        return ResponseEntity.ok().body(loja);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Loja loja){
        loja = service.insert(loja);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Loja loja, @PathVariable Integer id){
        loja.setId(id);
        loja = service.update(loja);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Loja>> findAll(){
        List<Loja> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
  
  
  
}

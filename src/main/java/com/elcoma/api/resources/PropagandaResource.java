package com.elcoma.api.resources;

import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.Propaganda;
import com.elcoma.api.services.PropagandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/propagandas")
public class PropagandaResource {

    @Autowired
    private PropagandaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Propaganda> find(@PathVariable Integer id){
        Propaganda propaganda = service.findById(id);
        return ResponseEntity.ok().body(propaganda);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Propaganda propaganda){
        propaganda = service.insert(propaganda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(propaganda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Propaganda propaganda, @PathVariable Integer id){
        propaganda.setId(id);
        propaganda = service.update(propaganda);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Propaganda>> findAll(){
        List<Propaganda> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}

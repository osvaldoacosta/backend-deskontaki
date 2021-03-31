package com.elcoma.api.resources;

import com.elcoma.api.domain.NotaFiscal;
import com.elcoma.api.services.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/notafiscal")
public class NotaFiscalResource {

    @Autowired
    private NotaFiscalService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody NotaFiscal notaFiscal) {
        notaFiscal = service.insert(notaFiscal);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(notaFiscal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NotaFiscal> findById(@PathVariable Integer id){
        NotaFiscal notaFiscal = service.findById(id);
        return  ResponseEntity.ok().body(notaFiscal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody NotaFiscal notaFiscal, @PathVariable Integer id){
        notaFiscal.setId(id);
        notaFiscal = service.update(notaFiscal);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NotaFiscal>> findAll(){
        List<NotaFiscal> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}

package com.elcoma.api.resources;

import com.elcoma.api.domain.NotaFiscal;
import com.elcoma.api.dto.LojaDTO;
import com.elcoma.api.dto.NotaFiscalDTO;
import com.elcoma.api.services.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/notafiscal")
public class NotaFiscalResource {

    @Autowired
    private NotaFiscalService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LojaDTO> insert(@Valid @RequestBody NotaFiscal notaFiscal) throws IOException {
        LojaDTO lojaDTO =  service.register(notaFiscal.getUrl());
        return ResponseEntity.ok().body(lojaDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NotaFiscal> findById(@PathVariable Integer id) {
        NotaFiscal notaFiscal = service.findById(id);
        return ResponseEntity.ok().body(notaFiscal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody NotaFiscal notaFiscal, @PathVariable Integer id) {
        notaFiscal.setId(id);
        notaFiscal = service.update(notaFiscal);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NotaFiscal>> findAll() {
        List<NotaFiscal> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/year/{year}", method = RequestMethod.GET)
    public ResponseEntity<List<NotaFiscalDTO>> findByYear(@PathVariable String year) {
        return ResponseEntity.ok().body(service.findByYear(year));
    }

    @RequestMapping(value = "/totalnotas/{idUsuario}/{dataCadastro}", method = RequestMethod.GET)
    public ResponseEntity<List<NotaFiscal>> findAllByUsuarioAndDataCadastro(
            @PathVariable Integer idUsuario,
            @PathVariable String dataCadastro
    ) throws ParseException {
        List<NotaFiscal> notaFiscalList = service.findAllByUsuarioAndDataCadastro(idUsuario, dataCadastro.trim());
        return ResponseEntity.ok().body(notaFiscalList);
    }
}

package com.elcoma.api.resources;

import com.elcoma.api.entity.Cupom;
import com.elcoma.api.dto.CupomDTO;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Cupom cupom) {
        cupom = service.insert(cupom);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cupom.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable Integer id) throws ObjectNotFoundException {
        Cupom obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cupom>> findAll() {
        List<Cupom> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/{mes}", method = RequestMethod.GET)
    public ResponseEntity<List<Cupom>> findAllByMonthAndUser(@PathVariable Integer id, @PathVariable String mes){
        List<Cupom> list = service.findAllByMonthAndUser(mes, id);
        return  ResponseEntity.ok().body(list);
    }

    @RequestMapping( method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStatus(@RequestParam Integer idUsuario,
                                             @RequestParam Integer idCupom){
        service.updateStatus(idCupom, idUsuario);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/loja", method = RequestMethod.GET)
    public ResponseEntity<List<CupomDTO>> findAllByLojaAndUsuario(@RequestParam String nomeLoja,
                                                                  @RequestParam Integer idUsuario){
        List<CupomDTO> list = service.findAllByLojaAndUsuario(nomeLoja, idUsuario);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/categoria", method = RequestMethod.GET)
    public ResponseEntity<List<CupomDTO>> findAllByCategoriaAndUsuario(@RequestParam Integer idCategoria,
                                                                       @RequestParam Integer idUsuario){
        List<CupomDTO> list = service.findAllByCategoriaAndUsuario(idCategoria, idUsuario);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<CupomDTO>> findAllByUsuario(@PathVariable Integer id){
        List<CupomDTO> cupomDTOList = service.findAllByUsuario(id);
        return ResponseEntity.ok().body(cupomDTOList);
    }

    @RequestMapping(value = "/mes/{mes}/{ano}", method = RequestMethod.GET)
    public ResponseEntity<List<CupomDTO>> findAllByMonthAndYear(@PathVariable String mes,
                                                                @PathVariable String ano){
        List<CupomDTO> cupomDTOList = service.findAllByMonthAndYear(mes, ano);
        return ResponseEntity.ok().body(cupomDTOList);
    }

    @RequestMapping(value = "/usuariolojamesano", method = RequestMethod.GET)
    public ResponseEntity<List<CupomDTO>> findAllByUserAndLojaAndMonthAndLoja(@RequestParam Integer idUsuario,
                                                                @RequestParam Integer idLoja,
                                                                @RequestParam Integer mes,
                                                                @RequestParam Integer ano){
        List<CupomDTO> ListDTO = service.findAllByUserAndLojaAndMonthAndLoja(idUsuario, idLoja, mes, ano);
        return  ResponseEntity.ok().body(ListDTO);
    }
}


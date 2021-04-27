package com.elcoma.api.services;

import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.NotaFiscal;
import com.elcoma.api.domain.Usuario;
import com.elcoma.api.repositories.NotaFiscalRepository;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository repository;
    @Autowired
    private LojaService lojaService;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public NotaFiscal insert(NotaFiscal notaFiscal){
        notaFiscal.setId(null);
        notaFiscal = repository.save(notaFiscal);
        return notaFiscal;
    }

    public NotaFiscal findById(Integer id){
        Optional<NotaFiscal> notaFiscal = repository.findById(id);

        return notaFiscal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: "+ id + ", Tipo: " + NotaFiscal.class.getName()
        ));
    }


    public NotaFiscal update(NotaFiscal notaFiscal) {
        findById(notaFiscal.getId());
        return repository.save(notaFiscal);

    }

    public void delete(Integer id){
        findById(id);
        repository.deleteById(id);
    }

    public List<NotaFiscal> findAll(){
        return  repository.findAll();
    }

    public void register(String url) {
        try{
            Document doc =Jsoup.connect(""+url).get();
            String erro = doc.getElementsByTag("erro").first().text();
            if(erro.equals("")){
                Usuario usuario = usuarioService.findByCpf(doc.getElementsByTag("CPF").first().text());
                Loja loja = lojaService.findByCnpj(doc.getElementsByTag("CNPJ").first().text());
                NotaFiscal notaFiscal = new NotaFiscal(null,
                        Double.parseDouble(doc.getElementsByTag("vNF").first().text()),
                        doc.getElementsByTag("infNFe").first().id(),
                        url,
                        doc.getElementsByTag("dhEmi").first().text(),
                        usuario, loja);
                repository.save(notaFiscal);
            }

        }catch (IOException e){

        }
    }
}

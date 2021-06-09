package com.elcoma.api.services;

import com.elcoma.api.domain.Loja;
import com.elcoma.api.domain.NotaFiscal;
import com.elcoma.api.domain.Usuario;
import com.elcoma.api.dto.LojaDTO;
import com.elcoma.api.dto.NotaFiscalDTO;
import com.elcoma.api.repositories.NotaFiscalRepository;
import com.elcoma.api.services.exceptions.DataConflictException;
import com.elcoma.api.services.exceptions.ObjectNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository repository;
    @Autowired
    private LojaService lojaService;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public NotaFiscal insert(NotaFiscal notaFiscal) {
        notaFiscal.setId(null);
        notaFiscal = repository.save(notaFiscal);
        return notaFiscal;
    }

    public NotaFiscal findById(Integer id) {
        Optional<NotaFiscal> notaFiscal = repository.findById(id);

        return notaFiscal.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: Id: " + id + ", Tipo: " + NotaFiscal.class.getName()
        ));
    }

    public NotaFiscal update(NotaFiscal notaFiscal) {
        findById(notaFiscal.getId());
        return repository.save(notaFiscal);

    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<NotaFiscal> findAll() {
        return repository.findAll();
    }

    public LojaDTO register(NotaFiscal notaFiscal) throws IOException {
        LojaDTO lojaDTO = new LojaDTO();
        Document doc = Jsoup.connect("" + notaFiscal.getUrl()).get();
        try {
            String erro = doc.getElementsByTag("erro").first().text();

            if (erro.equals("")) {
                Element elementoCpf = doc.getElementsByTag("CPF").first();

                if (elementoCpf != null) {

                    Loja loja = lojaService.findByCnpj(doc.getElementsByTag("CNPJ").first().text());
                    notaFiscal.setLoja(loja);

                    Usuario usuario = usuarioService.findByCpf(elementoCpf.text());
                    int idUsuarioResquet = notaFiscal.getUsuario().getId();
                    int idUsuarioNota = usuario.getId();
                    if ( idUsuarioResquet != idUsuarioNota ){
                        throw new DataConflictException("CPF do consumidor na NFC-e difere do CPF do usuário!");
                    }

                    String keyNfce = doc.getElementsByTag("infNFe").first().id();
                    notaFiscal.setKey(keyNfce);
                    List<NotaFiscal> listNotaFiscalValidarDuplicidade = findByKey(notaFiscal);
                    if(listNotaFiscalValidarDuplicidade.size() > 0){
                        throw new DataConflictException("Nota fiscal já cadastrada!");
                    }

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date dataEmissao = format.parse(doc.getElementsByTag("dhEmi")
                            .first().text().substring(0, 10));
                    notaFiscal.setDataEmissao(dataEmissao);

                    Date dataAtual = new Date(System.currentTimeMillis());
                    notaFiscal.setDataCadastro(dataAtual);

                    double valorTotal = Double.parseDouble(doc.getElementsByTag("vNF").first().text());
                    notaFiscal.setValor(valorTotal);

                    repository.save(notaFiscal);

                    lojaDTO.setCnpj(loja.getCnpj());
                    lojaDTO.setId(loja.getId());
                    lojaDTO.setNome(loja.getNome());

                } else {
                    throw new ObjectNotFoundException("CPF não encontrado na NFC-e");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lojaDTO;
    }

    public List<NotaFiscalDTO> findByYear(String year) {
        List<NotaFiscal> notaFiscalList = repository.findByYear(year);
        List<NotaFiscalDTO> notaFiscalDTOList = new ArrayList<>();
        for (NotaFiscal notaFiscal : notaFiscalList) {
            NotaFiscalDTO notaFiscalDTO = new NotaFiscalDTO(
                    notaFiscal.getKey(),
                    notaFiscal.getValor(),
                    notaFiscal.getDataEmissao(),
                    notaFiscal.getUsuario().getCpf()
            );
            notaFiscalDTOList.add(notaFiscalDTO);
        }
        return notaFiscalDTOList;
    }

    public List<NotaFiscal> findAllByUsuarioAndDataCadastro(Integer id, String dataCadastro) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Date dataCadastroFormat = format.parse(dataCadastro);
        Usuario usuario = usuarioService.findById(id);
        List<NotaFiscal> notaFiscalList = repository.findAllByUsuarioAndDataCadastro(id, dataCadastroFormat);
        return notaFiscalList;
    }

    public List<NotaFiscal> findByKey(NotaFiscal notaFiscal){
        return repository.findByKey(notaFiscal.getKey());
    }
}

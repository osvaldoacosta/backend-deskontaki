package com.elcoma.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name = "tb_notafiscal")
@Getter @Setter
public class NotaFiscal implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valor;
    @Column(name = "key_nfce")
    private String key;
    private String url;
    @Column(name= "data_emissao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataEmissao;
    @Column(name = "data_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataCadastro;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_loja")
    private Loja loja;

    public NotaFiscal() {
    }

    public NotaFiscal(Integer id, Double valor, String key, String url, Date dataEmissao,
                      Date dataCadastro, Usuario usuario, Loja loja) {
        this.id = id;
        this.valor = valor;
        this.usuario = usuario;
        this.loja= loja;
        this.url = url;
        this.key = key;
        this.dataEmissao = dataEmissao;
        this.dataCadastro = dataCadastro;
    }
}

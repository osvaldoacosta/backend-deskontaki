package com.elcoma.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_loja")
@Getter
@Setter
public class Loja implements Serializable {
    private static final long serialVersionUID = 1L;  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cnpj;
    private String categoria;
    private String nome;
    private String ponto;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<Cupom> cupons = new ArrayList();

    @JsonIgnore
    @OneToMany(mappedBy = "loja" )
    private List<NotaFiscal> notasFiscais = new ArrayList<>();

    public Loja() {
    }
  
    public Loja(Integer id, String cnpj, String categoria, String nome, String ponto, Cliente cliente) {
        this.id = id;
        this.cnpj = cnpj;
        this.categoria = categoria;
        this.nome = nome;
        this.ponto = ponto;
        this.cliente = cliente;
    }
}

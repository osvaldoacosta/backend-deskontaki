package com.elcoma.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "tb_cliente")
@Getter @Setter
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String cnpj;
    private String nome;
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Loja> listLoja;


    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Propaganda> propagandas;

    public Cliente() {
    }

    public Cliente(Integer id, String cnpj, String nome, String endereco) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
    }

}

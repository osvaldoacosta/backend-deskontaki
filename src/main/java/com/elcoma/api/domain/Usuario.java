package com.elcoma.api.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "tb_usuario")

@Getter @Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpf;
    private String nome;
    private String endereco;
    private String sexo;
    private String email;
    private String senha;
    private Date nascimento;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<NotaFiscal> notasFiscais = new ArrayList<>();
    public Usuario(){}
    public Usuario(int id, String cpf, String nome, String endereco, String sexo, String email, Date nascimento, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.email = email;
        this.nascimento = nascimento;
        this.senha = senha;
    }


}

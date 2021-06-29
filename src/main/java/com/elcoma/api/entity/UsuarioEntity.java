package com.elcoma.api.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "tb_usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpf;
    private String nome;
    private String endereco;
    private String sexo;
    private String email;
    private String senha;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date nascimento;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<NotaFiscal> notasFiscais = new ArrayList<>();

    public UsuarioEntity(int id, String cpf, String nome, String endereco, String sexo, String email, Date nascimento, String senha) {
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

package com.elcoma.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_propaganda")
@Getter
@Setter
public class Propaganda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String data_final;
    private String data_inicial;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Propaganda() {
    }

    public Propaganda(Integer id, String data_final, String data_inicial, String descricao, Cliente cliente) {
        this.id = id;
        this.data_final = data_final;
        this.data_inicial = data_inicial;
        this.descricao = descricao;
        this.cliente = cliente;
    }
}

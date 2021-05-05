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


@Entity(name = "tb_cupom")
@Getter @Setter
public class Cupom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date validade;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_loja")
    private Loja loja;

    public Cupom() {
    }

    public Cupom(Integer id, Date validade, Double valor) {
        this.id = id;
        this.validade = validade;
        this.valor = valor;
    }


}

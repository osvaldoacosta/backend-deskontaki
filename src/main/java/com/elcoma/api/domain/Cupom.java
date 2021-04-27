package com.elcoma.api.domain;

import com.elcoma.api.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "tb_cupom")
@Getter @Setter
public class Cupom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String validade;

    private Integer quantidade;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private CouponType tipoCupom;



    @ManyToOne
    @JoinColumn(name = "id_loja")
    private Loja loja;



    @JsonIgnore
    @OneToMany(mappedBy = "cupom")
    private List<UsuarioCupom> listUsuarioCupom = new ArrayList();

    public Cupom() {
    }


    public Cupom(Integer id, String dataInicial, String validade, Integer quantidade, Double valor, CouponType tipoCupom) {
        this.id = id;
        this.dataInicial = dataInicial;
        this.validade = validade;
        this.quantidade = quantidade;
        this.valor = valor;
        this.tipoCupom = tipoCupom;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cupom)) {
            return false;
        }
        Cupom other = (Cupom) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", dataInicial=" + dataInicial +
                ", validade=" + validade +
                ", quantidade=" + quantidade +
                ", tipoCupom=" + tipoCupom +
                ", valor=" + valor +
                ", lojaId=" + loja.getId() +
                '}';
    }



}

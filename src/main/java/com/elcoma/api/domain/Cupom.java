package com.elcoma.api.domain;

import com.elcoma.api.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_cupom")
@Getter @Setter
public class Cupom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date validade;

    private Integer quantidade;

    private Double valor;
    private String titulo;
    private String descricao;
    private String categoria;

    @Enumerated(EnumType.STRING)
    private CouponType tipoCupom;

    @ManyToOne
    @JoinColumn(name = "id_loja")
    private Loja loja;
}

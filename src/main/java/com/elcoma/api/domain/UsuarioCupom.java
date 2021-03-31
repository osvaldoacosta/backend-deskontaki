package com.elcoma.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_usuario_cupom")
@Getter @Setter
public class UsuarioCupom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cupom")
    private Cupom cupom;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String status;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data_uso;

    private UsuarioCupom(){}

    public UsuarioCupom(Cupom cupom, Usuario usuario, String status, Date data_uso) {
        this.cupom = cupom;
        this.usuario = usuario;
        this.status = status;
        this.data_uso = data_uso;
    }
}

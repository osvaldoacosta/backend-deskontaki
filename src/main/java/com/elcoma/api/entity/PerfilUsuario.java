package com.elcoma.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_perfil_usuario")
@Getter @Setter
public class PerfilUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String obs;
    private String preferencias;
    private String horarios;

    public PerfilUsuario() {
    }

    public PerfilUsuario(Integer id, String obs, String preferencias, String horarios) {
        this.id = id;
        this.obs = obs;
        this.preferencias = preferencias;
        this.horarios = horarios;
    }
}

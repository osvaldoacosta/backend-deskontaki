package com.elcoma.api.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LojaDTO implements Serializable {
    private Integer id;
    private String cnpj;
    private String nome;
}

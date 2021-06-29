package com.elcoma.api.mapper;

import com.elcoma.api.dto.UsuarioDTO;
import com.elcoma.api.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsuarioMapper {

    public static UsuarioDTO toDto(UsuarioEntity entity){
        log.info("Iniciando conversão para dto: {}", entity);
        UsuarioDTO dto = UsuarioDTO.builder()
                .nome(entity.getNome())
                .dataNascimento(entity.getNascimento())
                .sexo(entity.getSexo()).build();
        log.info("Conversão para dto realizada: {}", dto);
        return dto;
    }

    public static UsuarioEntity toEnity(UsuarioDTO dto){
        log.info("Iniciando conversão para entidade: {}", dto);
        UsuarioEntity entity = UsuarioEntity.builder()
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .nascimento(dto.getDataNascimento())
                .sexo(dto.getSexo()).build();
        log.info("Conversão para entidade realizada: {}", entity);
        return entity;
    }
}

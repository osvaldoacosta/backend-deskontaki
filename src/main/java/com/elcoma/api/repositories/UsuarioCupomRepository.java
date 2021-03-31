package com.elcoma.api.repositories;

import com.elcoma.api.domain.UsuarioCupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioCupomRepository extends JpaRepository<UsuarioCupom, Integer> {
    @Query(value = " select uc.id_cupom, uc.id_usuario, uc.status, uc.data_uso, " +
            " uc.id from tb_usuario_cupom uc " +
            " where Month(uc.data_uso) = :mes and uc.id_usuario = :id_usuario and " +
            " uc.status like 'U'", nativeQuery = true)
    public List<UsuarioCupom> findAllByMothAndUsuario(@Param("mes") String mes, @Param("id_usuario") Integer id);
}

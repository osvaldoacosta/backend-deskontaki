package com.elcoma.api.repositories;

import com.elcoma.api.domain.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {

    @Query(value = "SELECT c.id, c.validade, c.valor, c.id_loja FROM tb_cupom c " +
            " inner join tb_usuario_cupom uc on uc.id_cupom = c.id " +
            " where Month(uc.data_uso) = :mes and uc.id_usuario = :id_usuario ", nativeQuery = true)
    public List<Cupom> findAllByMothAndUsuario(@Param("mes") String mes, @Param("id_usuario") Integer id_usuario);
}

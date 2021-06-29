package com.elcoma.api.repositories;

import com.elcoma.api.entity.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {


    @Query(value = " SELECT * FROM tb_notafiscal nf WHERE year(nf.data_emissao) = :year ; ", nativeQuery = true)
    public List<NotaFiscal> findByYear(@Param("year")String year);

    @Query(value = " SELECT * FROM tb_notafiscal nf WHERE " +
            " nf.id_usuario = :id AND nf.data_cadastro >= :dataCadastro ; ", nativeQuery = true)
    List<NotaFiscal> findAllByUsuarioAndDataCadastro(@Param("id") Integer id,
                                                     @Param("dataCadastro") Date dataCadastro);

    @Query(value = " SELECT * FROM tb_notafiscal nf WHERE nf.key_nfce like :keyNfce ", nativeQuery = true)
    List<NotaFiscal> findByKey(@Param("keyNfce") String keyNfce);
}

package com.elcoma.api.repositories;

import com.elcoma.api.domain.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {


    @Query(value = "SELECT * FROM tb_notafiscal nf WHERE year(nf.data_emissao) = :year ; ", nativeQuery = true)
    public List<NotaFiscal> findByYear(@Param("year")String year);
}

package com.elcoma.api.repositories;

import com.elcoma.api.domain.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

    public Optional<Loja> findByCnpj(String cnpj);
}

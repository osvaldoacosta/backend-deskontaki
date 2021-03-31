package com.elcoma.api.repositories;

import com.elcoma.api.domain.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

}

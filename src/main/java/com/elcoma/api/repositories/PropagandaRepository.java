package com.elcoma.api.repositories;
import com.elcoma.api.entity.Propaganda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropagandaRepository extends JpaRepository<Propaganda, Integer> {
}

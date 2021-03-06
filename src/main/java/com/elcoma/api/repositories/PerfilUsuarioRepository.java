package com.elcoma.api.repositories;

import com.elcoma.api.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {
}

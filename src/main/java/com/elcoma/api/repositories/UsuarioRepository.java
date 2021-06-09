package com.elcoma.api.repositories;

import com.elcoma.api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCpf(String cpf);

    @Query(value = " select * from tb_usuario u inner join tb_perfil_usuario " +
            "pu on u.id = pu.id_usuario where pu.id_perfil = :idPerfil ", nativeQuery = true)
    List<Usuario> findAllByPerfil(@Param("idPerfil")Integer idPerfil);
}

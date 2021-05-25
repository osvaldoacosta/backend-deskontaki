package com.elcoma.api.repositories;

import com.elcoma.api.domain.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {

    @Query(value = "SELECT c.id, c.validade, c.valor, c.id_loja FROM tb_cupom c " +
            " inner join tb_usuario_cupom uc on uc.id_cupom = c.id " +
            " where Month(uc.data_uso) = :mes and uc.id_usuario = :id_usuario ", nativeQuery = true)
    public List<Cupom> findAllByMothAndUsuario(@Param("mes") String mes, @Param("id_usuario") Integer id_usuario);

    @Modifying
    @Transactional
    @Query(value = " UPDATE tb_usuario_cupom  uc SET uc.status = :status " +
                   " WHERE uc.id_cupom = :idCupom AND uc.id_usuario = :idUsuario ", nativeQuery = true)
    public void updateStatus(@Param("idCupom")Integer idCupom,
                             @Param("idUsuario")Integer idUsuario,
                             @Param("status")String status);
    @Modifying
    @Transactional
    @Query(value = " INSERT tb_usuario_cupom (id_cupom, id_usuario, status) " +
                   " VALUES(:idCupom, :idUsuario, 'D') ", nativeQuery = true)
    public void sendCuponsForUsuarios(@Param("idCupom")Integer idCupom,
                                      @Param("idUsuario")Integer idUsuario);

    @Query(value =  "SELECT c.id, c.titulo , c.descricao, c.valor, c.data_inicial," +
                    " c.validade, c.id_loja, c.categoria, c.quantidade " +
                    " FROM " +
                    " tb_cupom c " +
                    " INNER JOIN " +
                    " tb_usuario_cupom uc " +
                    " ON " +
                    " c.id = uc.id_cupom " +
                    " INNER JOIN " +
                    " tb_loja l " +
                    " ON " +
                    " l.id = c.id_loja " +
                    " WHERE " +
                    " l.nome LIKE :nome_loja AND uc.id_usuario = :id_usuario ", nativeQuery = true)
    public List<Cupom> findAllByLojaAndUsuario( @Param("nome_loja")String nomeLoja,
                                               @Param("id_usuario")Integer idUsuario);

    @Query(value =  "SELECT c.id, c.titulo , c.descricao, c.valor, c.data_inicial," +
            " c.validade, c.id_loja, c.id_categoria, c.quantidade " +
            " FROM " +
            " tb_cupom c " +
            " INNER JOIN " +
            " tb_usuario_cupom uc " +
            " ON " +
            " c.id = uc.id_cupom " +
            " WHERE " +
            " c.id_categoria = :idCategoria AND uc.id_usuario = :id_usuario ", nativeQuery = true)
    List<Cupom> findAllByCategoriaAndUsuario(@Param("idCategoria") Integer idCategoria,
                                             @Param("id_usuario") Integer idUsuario);

    @Query(value =  " SELECT c.id, c.titulo , c.descricao, c.valor, c.data_inicial," +
            " c.validade, c.id_loja, c.id_categoria, c.quantidade " +
            " FROM " +
            " tb_cupom c " +
            " INNER JOIN " +
            " tb_usuario_cupom uc " +
            " ON " +
            " c.id = uc.id_cupom " +
            " WHERE " +
            " uc.id_usuario = :id_usuario ", nativeQuery = true)
    List<Cupom> findAllByUsuario(@Param("id_usuario") Integer id);
}

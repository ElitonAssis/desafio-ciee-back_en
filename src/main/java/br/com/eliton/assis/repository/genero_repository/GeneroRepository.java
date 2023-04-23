package br.com.eliton.assis.repository.genero_repository;

import br.com.eliton.assis.model.base_entity.game.CategoriaEntity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<CategoriaEntity, Integer> {

    @Query(value = "SELECT * FROM CIEE_GAME_GENERO WHERE DELETADO = 0 ", nativeQuery = true)
    Optional<List<CategoriaEntity>> findAllNotDeletede();


    @Query(value = "SELECT * FROM CIEE_GAME_GENERO  as genero " +
            " INNER JOIN game_genero as GG on genero.ID = gg.genero_id " +
            " WHERE DELETADO = 0 AND " +
            " gg.game_id = :id "
            , nativeQuery = true)
    Optional<List<CategoriaEntity>> findCategorias(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM game_genero" +
            " WHERE GENERO_ID = :categoriaId AND GAME_ID = :gameId", nativeQuery = true)
    void removeFromGame(@Param("gameId") Integer gameId, @Param("categoriaId") Integer categoriaId);
}

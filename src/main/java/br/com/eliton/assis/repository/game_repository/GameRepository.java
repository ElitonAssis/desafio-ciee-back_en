package br.com.eliton.assis.repository.game_repository;

import br.com.eliton.assis.model.base_entity.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

//        @Query(value = "SELECT GAME.ID  FROM CIEE_GAME_CADASTRO as GAME " +
//            " INNER JOIN game_genero as GG on GAME.ID = GG.game_id " +
//            " INNER JOIN CIEE_GAME_GENERO as CATEGORIA on CATEGORIA.ID = GG.genero_id " +
//            " WHERE GAME.DELETADO = false", nativeQuery = true)
//    Optional<List<GameEntity>> findGameList();
@Query(value = "SELECT DISTINCT GAME FROM GameEntity GAME " +
        "LEFT JOIN FETCH GAME.generos " +
        "WHERE GAME.deletado = false")
Optional<List<GameEntity>> findGameList();



}
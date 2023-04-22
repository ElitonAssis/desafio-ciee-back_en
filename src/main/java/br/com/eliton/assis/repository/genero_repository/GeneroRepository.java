package br.com.eliton.assis.repository.genero_repository;

import br.com.eliton.assis.model.base_entity.game.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<CategoriaEntity, Integer> {

    @Query(value ="SELECT * FROM CIEE_GAME_GENERO WHERE DELETADO = 0 ",nativeQuery = true)
    Optional<List<CategoriaEntity>> findAllNotDeletede();
}

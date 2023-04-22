package br.com.eliton.assis.repository.game_repository;

import br.com.eliton.assis.model.base_entity.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository  extends JpaRepository<GameEntity, Integer> {
}

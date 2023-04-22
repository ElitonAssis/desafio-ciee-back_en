package br.com.eliton.assis.services.game_service;

import br.com.eliton.assis.repository.game_repository.GameRepository;
import br.com.eliton.assis.model.base_entity.game.GameEntity;
import br.com.eliton.assis.model.base_entity.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class GameService {
    @Autowired()
    GameRepository gameRepository;

    private ResponseMessage responseMessage(String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(msg);
        return responseMessage;
    }

    public ResponseEntity<?> save(GameEntity obj) {
        GameEntity gameEntity =  new GameEntity();
        try {
            List<GameEntity> e = this.gameRepository.findAll();

            if (Objects.isNull(obj)) throw new Exception("Valor não encontrado");
            this.gameRepository.save(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseMessage("SALVO COM SUCESSO"), HttpStatus.OK);
    }
}

package br.com.eliton.assis.rest.game_controller;

import br.com.eliton.assis.model.base_entity.game.GameEntity;
import br.com.eliton.assis.services.game_service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;
    @GetMapping("teste")
    public ResponseEntity<?> teste() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("cadastro-jogo")
    public ResponseEntity<?> cadastroJogo(@RequestBody GameEntity obj) {


       return this.gameService.save(obj);
    }
}

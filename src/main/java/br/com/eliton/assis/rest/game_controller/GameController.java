package br.com.eliton.assis.rest.game_controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {
    @GetMapping("teste")
    public ResponseEntity<?> teste() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("cadastro-jogo")
    public ResponseEntity<?> cadastroJogo(@RequestBody Map<String, String> obj) {
        return new ResponseEntity<>(new String("231"), HttpStatus.OK);
    }
}

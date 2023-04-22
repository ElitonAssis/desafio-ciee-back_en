package br.com.eliton.assis.rest.genero_controller;

import br.com.eliton.assis.model.base_entity.game.CategoriaEntity;
import br.com.eliton.assis.services.genero_service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    GeneroService generoService;

    @PostMapping("cadastro-genero")
    public ResponseEntity<?> save(@RequestBody CategoriaEntity obj) {
        return this.generoService.save(obj);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAll() {
        return this.generoService.getAll();
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return this.generoService.deleteTemp(id);
    }
}

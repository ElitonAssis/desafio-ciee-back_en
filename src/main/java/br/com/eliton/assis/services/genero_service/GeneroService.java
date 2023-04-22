package br.com.eliton.assis.services.genero_service;

import br.com.eliton.assis.model.base_entity.game.CategoriaEntity;
import br.com.eliton.assis.model.base_entity.utils.ResponseMessage;
import br.com.eliton.assis.repository.genero_repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    private ResponseMessage responseMessage(String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(msg);
        return responseMessage;
    }

    public ResponseEntity<?> save(CategoriaEntity obj) {
        try {
            if (Objects.isNull(obj)) throw new Exception("Valor não encontrado");
            this.generoRepository.save(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseMessage("SALVO COM SUCESSO"), HttpStatus.OK);
    }

    public ResponseEntity<?> getAll() {
        List<CategoriaEntity> categoriaEntityList = new ArrayList<>();
        try {
            categoriaEntityList = this.generoRepository.findAllNotDeletede().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoriaEntityList, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteTemp(Integer id) {
        try {
            CategoriaEntity categoria = this.generoRepository.findById(id).orElse(null);
            if (Objects.isNull(categoria)) throw new Exception("Não foi possível deletar");
            categoria.setDeletado(true);
            this.generoRepository.save(categoria);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseMessage("Deletado com sucesso"), HttpStatus.OK);
    }

}

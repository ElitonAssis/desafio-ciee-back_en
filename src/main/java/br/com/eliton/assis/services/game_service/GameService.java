package br.com.eliton.assis.services.game_service;

import br.com.eliton.assis.model.base_entity.game.CategoriaEntity;
import br.com.eliton.assis.repository.game_repository.GameRepository;
import br.com.eliton.assis.model.base_entity.game.GameEntity;
import br.com.eliton.assis.model.base_entity.utils.ResponseMessage;
import br.com.eliton.assis.repository.genero_repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired()
    GameRepository gameRepository;
    @Autowired
    GeneroRepository categoriaRepository;

    private ResponseMessage responseMessage(String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(msg);
        return responseMessage;
    }

    public ResponseEntity<?> save(GameEntity obj) {
        GameEntity gameEntity = new GameEntity();
        try {
            if (Objects.isNull(obj)) throw new Exception("Valor não encontrado");

            if (!obj.getCategoriaEntityList().isEmpty())
                for (CategoriaEntity categoria : obj.getCategoriaEntityList()) obj.getGeneros().add(categoria);

            this.gameRepository.save(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseMessage("SALVO COM SUCESSO"), HttpStatus.OK);
    }

    public ResponseEntity<?> getAll() {
        try {
            List<GameEntity> gameEntityList = this.gameRepository.findGameList().orElse(null);
            if (Objects.isNull(gameEntityList) || gameEntityList.isEmpty())
                return new ResponseEntity<>(responseMessage("Nenhum jogo encontrado"), HttpStatus.BAD_REQUEST);
            Map<String, List<GameEntity>> ret = createMap(gameEntityList);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch
        (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    private Map<String, List<GameEntity>> createMap(List<GameEntity> list) throws Exception {
        Map<String, List<GameEntity>> map = new HashMap<>();

        List<CategoriaEntity> categoriaEntityList = new ArrayList<>();

        for (GameEntity gameEntity : list) {
            if (gameEntity.getGeneros().isEmpty()) continue;
            categoriaEntityList.addAll(gameEntity.getGeneros());
        }
        categoriaEntityList = categoriaEntityList.stream().distinct().collect(Collectors.toList());
        for (CategoriaEntity categoria : categoriaEntityList) {
            map.put(categoria.getNome(), list.stream().filter(g -> g.getGeneros().stream().filter(categoriaEntity ->
                    categoriaEntity.getId().equals(categoria.getId())).toList().size() > 0).collect(Collectors.toList()));
        }
        List<GameEntity> temp = list.stream().filter(gameEntity -> gameEntity.getGeneros().isEmpty()).toList();
        if (!temp.isEmpty())
            map.put("Sem categoria", temp);

        return map;
    }

    public ResponseEntity<?> deleteTemp(GameEntity game) {
        try {
            game.setDeletado(true);
            this.gameRepository.save(game);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseMessage("Deletado com sucesso"), HttpStatus.OK);
    }

    public ResponseEntity<?> getCategoria(Integer id) {
        try {
            List<CategoriaEntity> lista = this.categoriaRepository.findCategorias(id).orElse(null);
            if (Objects.isNull(lista) || lista.isEmpty()) throw new Exception("Não foi possível encontrar gêneros cadastros");

            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package br.com.eliton.assis.model.base_entity.game;

import br.com.eliton.assis.model.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CIEE_GAME_GENERO")
public class CategoriaEntity extends BaseEntity {
    @Column(name = "NOME")
    String nome;

    @ManyToMany(mappedBy = "generos")
    //@JsonBackReference
    private Set<GameEntity> games = new HashSet<>();
}

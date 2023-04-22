package br.com.eliton.assis.model.base_entity.game;

import br.com.eliton.assis.model.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CIEE_GAME_GENERO")
public class CategoriaEntity extends BaseEntity {
    @Column(name = "NOME")
    String nome;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    @JsonBackReference
    GameEntity gameEntity;
}

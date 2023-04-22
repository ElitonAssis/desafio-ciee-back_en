package br.com.eliton.assis.model.base_entity.game;

import br.com.eliton.assis.model.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "CIEE_GAME_CADASTRO")
public class GameEntity extends BaseEntity {
    @Column(name = "TITULO")
    String titulo;
    @Column(name = "DESCRICAO")
    String descricao;
    @Column(name = "DATA")
    Date lancamento;
    @Column(name = "DESENVOLVEDORA")
    String desenvolvedora;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "game_genero",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENERO_ID"))
    //@JsonManagedReference
    private Set<CategoriaEntity> generos = new HashSet<>();

    @PreUpdate
    @PrePersist
    public void preUpdate() {
        if (generos != null) {
            for (CategoriaEntity categoria : generos) {
                categoria.getGames().add(this);
            }
        }
    }

}

package br.com.eliton.assis.model.base_entity.game;

import br.com.eliton.assis.model.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @Transient
    List<CategoriaEntity>categoriaEntityList;

    @JsonIgnore
    //@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")

    @ManyToMany
    @JoinTable(
            name = "game_genero",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENERO_ID"))

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

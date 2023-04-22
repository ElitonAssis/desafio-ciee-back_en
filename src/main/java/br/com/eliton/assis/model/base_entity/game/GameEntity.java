package br.com.eliton.assis.model.base_entity.game;

import br.com.eliton.assis.model.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gameEntity")
    @JsonManagedReference
    List<CategoriaEntity> categoriaEntityList;

    @PrePersist
    @PreUpdate
    public void preencher() {
        if (categoriaEntityList != null)
            categoriaEntityList.forEach(c -> c.setGameEntity(this));
    }
}

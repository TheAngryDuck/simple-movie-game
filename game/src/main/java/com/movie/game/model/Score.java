package com.movie.game.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Score  extends BaseEntity{
    private int points;
    private String difficulty;
}

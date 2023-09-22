package com.movie.game.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Getter
@Setter
public class Movie  extends BaseEntity{

    private String originalTitle;
    @Column(length = 1000)
    private String overview;
    private float popularity;
    private Date releaseDate;
    private BigInteger revenue;
    private int runtime;
    private String tagline;
    private String Title;
    private int voteAverage;
    private int voteCount;
}

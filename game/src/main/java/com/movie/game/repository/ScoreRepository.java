package com.movie.game.repository;

import com.movie.game.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ScoreRepository  extends JpaRepository<Score, BigInteger> {

    Score findTopScoreAndDifficultyContaining(String difficulty);
}

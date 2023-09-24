package com.movie.game.repository;

import com.movie.game.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ScoreRepository  extends JpaRepository<Score, BigInteger> {

    @Query("SELECT MAX(points) from Score WHERE difficulty = 'Easy'")
    Integer findTopScoreOnEasy();

    @Query("SELECT MAX(points) from Score WHERE difficulty = 'Hard'")
    Integer findTopScoreOnHard();

}

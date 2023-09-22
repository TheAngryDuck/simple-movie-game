package com.movie.game.repository;

import com.movie.game.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Movie, BigInteger> {

    List<Movie> findByRevenueBetween(BigInteger startRevenue, BigInteger endRevenue);
    List<Movie> findByVoteAverageBetween(Integer startVoteAverage, Integer endVoteAverage);
    List<Movie> findByRuntimeBetween(Integer startRuntime, Integer endRuntime);
    List<Movie> findByPopularityBetween(double startPopularity, double endPopularity);
}

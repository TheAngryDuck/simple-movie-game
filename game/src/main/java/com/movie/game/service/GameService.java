package com.movie.game.service;

import com.movie.game.model.Movie;
import com.movie.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;

    public Optional<Movie> getById(BigInteger id){return  repository.findById(id);}
    public List<Movie> getByRevenue(BigInteger startRevenue, BigInteger endRevenue){
        return repository.findByRevenueBetween(startRevenue, endRevenue);}
    public List<Movie> getByVoteAverage(Integer startVoteAverage, Integer endVoteAverage){
        return repository.findByVoteAverageBetween(startVoteAverage, endVoteAverage);}
    public List<Movie> getByRuntime(Integer startRuntime, Integer endRuntime){
        return repository.findByRuntimeBetween(startRuntime, endRuntime);}
    public List<Movie> getByPopularity(double startPopularity, double endPopularity){
        return repository.findByPopularityBetween(startPopularity, endPopularity);}
}

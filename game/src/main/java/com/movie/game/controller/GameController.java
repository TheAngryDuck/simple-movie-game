package com.movie.game.controller;

import com.movie.game.helper.MovieHelper;
import com.movie.game.enums.Type;
import com.movie.game.model.Movie;
import com.movie.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService service;

    private final MovieHelper helper;

    @GetMapping
    public Movie getById(){ return service.getById(helper.getId()).get(); }

    @GetMapping("/revenue/{number}")
    public Movie getByRevenue(@PathVariable BigInteger number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.REVENUE,number,0,0)){
                movies = service.getByRevenue(number.subtract(new BigInteger("100000000")), number.subtract(new BigInteger("1")));
            }else {
                movies = service.getByRevenue(number.add(new BigInteger("1")), number.add(new BigInteger("100000000")));
            }
        }else {
            if (helper.isLowerValue(Type.REVENUE,number,0,0)){
                movies = service.getByRevenue(number.add(new BigInteger("1")), number.add(new BigInteger("100000000")));
            }else{
                movies = service.getByRevenue(number.subtract(new BigInteger("100000000")), number.subtract(new BigInteger("1")));
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/vote/{number}")
    public Movie getByVoteAverage(@PathVariable int number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.VOTE,new BigInteger("0"),number,0)){
                movies = service.getByVoteAverage(number-2, number-1);
            }else{
                movies = service.getByVoteAverage(number+1, number+2);
            }
        }else {
            if (helper.isLowerValue(Type.VOTE,new BigInteger("0"),number,0)){
                movies = service.getByVoteAverage(number+1, number+2);
            }else {
                movies = service.getByVoteAverage(number-2, number-1);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/runtime/{number}")
    public Movie getByRuntime(@PathVariable int number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                movies = service.getByRuntime(number-20, number-1);
            }else{
                movies = service.getByRuntime(number+1, number+20);
            }
        }else {
            if (helper.isLowerValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                movies = service.getByRuntime(number+1, number+20);
            }else {
                movies = service.getByRuntime(number-20, number-1);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/popularity/{number}")
    public Movie getByPopularity(@PathVariable double number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                movies = service.getByPopularity(number-5, number-0.001);
            }else{
                movies = service.getByPopularity(number+0.001, number+5);
            }
        }else {
            if (helper.isLowerValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                movies = service.getByPopularity(number+0.001, number+5);
            }else {
                movies = service.getByPopularity(number-5, number-0.001);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }
}

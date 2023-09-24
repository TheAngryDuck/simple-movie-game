package com.movie.game.controller;

import com.movie.game.helper.MovieHelper;
import com.movie.game.enums.Type;
import com.movie.game.model.Movie;
import com.movie.game.model.Score;
import com.movie.game.repository.ScoreRepository;
import com.movie.game.service.GameService;
import com.movie.game.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    private final ScoreService scoreService;

    private final MovieHelper helper;

    @GetMapping
    public Movie getById(){ return gameService.getById(helper.getId()).get(); }

    @GetMapping("/score/easy")
    public Integer getByEasy(){
       return scoreService.getHighestEasyScore();
    }

    @GetMapping("/score/hard")
    public Integer getByHard(){
        return scoreService.getHighestHardScore();
    }

    @PostMapping("/score")
    public ResponseEntity<Score> addNewScore(@RequestBody Score score){
        if (score != null){
            return ResponseEntity.ok().body(scoreService.addNewScore(score));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/revenue")
    public Movie getByRevenue(@RequestParam BigInteger number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.REVENUE,number,0,0)){
                movies = gameService.getByRevenue(number.subtract(new BigInteger("100000000")), number.subtract(new BigInteger("1")));
            }else {
                movies = gameService.getByRevenue(number.add(new BigInteger("1")), number.add(new BigInteger("100000000")));
            }
        }else {
            if (helper.isLowerValue(Type.REVENUE,number,0,0)){
                movies = gameService.getByRevenue(number.add(new BigInteger("1")), number.add(new BigInteger("100000000")));
            }else{
                movies = gameService.getByRevenue(number.subtract(new BigInteger("100000000")), number.subtract(new BigInteger("1")));
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/vote")
    public Movie getByVoteAverage(@RequestParam int number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.VOTE,new BigInteger("0"),number,0)){
                movies = gameService.getByVoteAverage(number-2, number-1);
            }else{
                movies = gameService.getByVoteAverage(number+1, number+2);
            }
        }else {
            if (helper.isLowerValue(Type.VOTE,new BigInteger("0"),number,0)){
                movies = gameService.getByVoteAverage(number+1, number+2);
            }else {
                movies = gameService.getByVoteAverage(number-2, number-1);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/runtime")
    public Movie getByRuntime(@RequestParam int number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                movies = gameService.getByRuntime(number-20, number-1);
            }else{
                movies = gameService.getByRuntime(number+1, number+20);
            }
        }else {
            if (helper.isLowerValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                movies = gameService.getByRuntime(number+1, number+20);
            }else {
                movies = gameService.getByRuntime(number-20, number-1);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/popularity")
    public Movie getByPopularity(@RequestParam double number){
        Random random = new Random();
        List<Movie> movies;
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                movies = gameService.getByPopularity(number-5, number-0.001);
            }else{
                movies = gameService.getByPopularity(number+0.001, number+5);
            }
        }else {
            if (helper.isLowerValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                movies = gameService.getByPopularity(number+0.001, number+5);
            }else {
                movies = gameService.getByPopularity(number-5, number-0.001);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }
}

package com.movie.game.controller;

import com.movie.game.helper.MovieHelper;
import com.movie.game.enums.Type;
import com.movie.game.model.Movie;
import com.movie.game.model.Score;
import com.movie.game.service.GameService;
import com.movie.game.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.movie.game.enums.TypeLimits.*;

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
    public Movie getByRevenue(@RequestParam BigInteger number, @RequestParam String type){
        Random random = new Random();
        List<Movie> movies;
        BigInteger upperLimiter;
        BigInteger lowerLimiter;
        if (Objects.equals(type, "Easy")){
            upperLimiter = revenueUpperLimit;
            lowerLimiter = revenueLowerLimit;
        }else{
            upperLimiter = number.add(new BigInteger("100000000"));
            lowerLimiter = number.subtract(new BigInteger("100000000"));
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.REVENUE,number,0,0)){
                movies = gameService.getByRevenue(lowerLimiter, number.subtract(new BigInteger("1")));
            }else {
                movies = gameService.getByRevenue(number.add(new BigInteger("1")), upperLimiter);
            }
        }else {
            if (helper.isLowerValue(Type.REVENUE,number,0,0)){
                movies = gameService.getByRevenue(number.add(new BigInteger("1")), upperLimiter);
            }else{
                movies = gameService.getByRevenue(lowerLimiter, number.subtract(new BigInteger("1")));
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/vote")
    public Movie getByVoteAverage(@RequestParam int number, @RequestParam String type){
        Random random = new Random();
        List<Movie> movies;
        Integer upperLimiter;
        Integer lowerLimiter;
        if (Objects.equals(type, "Easy")){
            upperLimiter = voteUpperLimit;
            lowerLimiter = voteLowerLimit;
        }else{
            upperLimiter = number+2;
            lowerLimiter = number-2;
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.VOTE,new BigInteger("0"),number,0)){
                movies = gameService.getByVoteAverage(lowerLimiter, number-1);
            }else{
                movies = gameService.getByVoteAverage(number+1, upperLimiter);
            }
        }else {
            if (helper.isLowerValue(Type.VOTE,new BigInteger("0"),number,0)){
                movies = gameService.getByVoteAverage(number+1, upperLimiter);
            }else {
                movies = gameService.getByVoteAverage(lowerLimiter, number-1);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/runtime")
    public Movie getByRuntime(@RequestParam int number, @RequestParam String type){
        Random random = new Random();
        List<Movie> movies;
        Integer upperLimiter;
        Integer lowerLimiter;
        if (Objects.equals(type, "Easy")){
            upperLimiter = runtimeUpperLimit;
            lowerLimiter = runtimeLowerLimit;
        }else{
            upperLimiter = number+20;
            lowerLimiter = number-20;
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                movies = gameService.getByRuntime(lowerLimiter, number-1);
            }else{
                movies = gameService.getByRuntime(number+1, upperLimiter);
            }
        }else {
            if (helper.isLowerValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                movies = gameService.getByRuntime(number+1, upperLimiter);
            }else {
                movies = gameService.getByRuntime(lowerLimiter, number-1);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }

    @GetMapping("/popularity")
    public Movie getByPopularity(@RequestParam double number, @RequestParam String type){
        Random random = new Random();
        List<Movie> movies;
        double upperLimiter;
        double lowerLimiter;
        if (Objects.equals(type, "Easy")){
            upperLimiter = popularityUpperLimit;
            lowerLimiter = popularityLowerLimit;
        }else{
            upperLimiter = number+5;
            lowerLimiter = number-5;
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                movies = gameService.getByPopularity(lowerLimiter, number-0.001);
            }else{
                movies = gameService.getByPopularity(number+0.001, upperLimiter);
            }
        }else {
            if (helper.isLowerValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                movies = gameService.getByPopularity(number+0.001, upperLimiter);
            }else {
                movies = gameService.getByPopularity(lowerLimiter, number-0.001);
            }
        }
        return movies.get(random.nextInt(movies.size()));
    }
}

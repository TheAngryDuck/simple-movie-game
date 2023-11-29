package com.movie.game.controller;

import com.movie.game.model.Movie;
import com.movie.game.model.Score;
import com.movie.game.service.GameService;
import com.movie.game.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    private final ScoreService scoreService;


    @GetMapping
    public Movie getById(){ return gameService.getById(); }

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
        return scoreService.addNewScore(score);
    }

    @GetMapping("/revenue")
    public Movie getByRevenue(@RequestParam BigInteger number, @RequestParam String type){
        return gameService.getByRevenue(number,type);
    }

    @GetMapping("/vote")
    public Movie getByVoteAverage(@RequestParam int number, @RequestParam String type){
        return gameService.getByVoteAverage(number, type);
    }

    @GetMapping("/runtime")
    public Movie getByRuntime(@RequestParam int number, @RequestParam String type){
        return gameService.getByRuntime(number,type);
    }

    @GetMapping("/popularity")
    public Movie getByPopularity(@RequestParam double number, @RequestParam String type){
        return gameService.getByPopularity(number,type);
    }
}

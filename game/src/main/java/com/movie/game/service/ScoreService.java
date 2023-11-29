package com.movie.game.service;


import com.movie.game.model.Score;
import com.movie.game.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository repository;

    public Integer getHighestEasyScore(){return repository.findTopScoreOnEasy();}

    public Integer getHighestHardScore(){return repository.findTopScoreOnHard();}

    public ResponseEntity<Score> addNewScore(Score score){
        if (score != null){
            return ResponseEntity.ok().body(repository.save(score));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}

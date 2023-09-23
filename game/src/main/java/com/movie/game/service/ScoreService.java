package com.movie.game.service;


import com.movie.game.model.Score;
import com.movie.game.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository repository;

    public Score getHighestEasyScore(){return repository.findTopScoreAndDifficultyContaining("easy");}

    public Score getHIghestHardScore(){return repository.findTopScoreAndDifficultyContaining("hard");}

}

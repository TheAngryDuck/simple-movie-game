package com.movie.game.service;

import com.movie.game.enums.Type;
import com.movie.game.helper.MovieHelper;
import com.movie.game.model.Movie;
import com.movie.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.movie.game.enums.TypeLimits.*;
import static com.movie.game.enums.TypeLimits.voteLowerLimit;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;

    private final MovieHelper helper;

    public Movie getById(){
        return  repository.findById(helper.getId()).get();
    }
    public Movie getByRevenue(BigInteger number, String type){
        Random random = new Random();
        BigInteger upperLimiter;
        BigInteger lowerLimiter;
        BigInteger startRevenue;
        BigInteger endRevenue;
        if (Objects.equals(type, "Easy")){
            upperLimiter = revenueUpperLimit;
            lowerLimiter = revenueLowerLimit;
        }else{
            upperLimiter = number.add(new BigInteger("100000000"));
            lowerLimiter = number.subtract(new BigInteger("100000000"));
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.REVENUE,number,0,0)){
                startRevenue = lowerLimiter;
                endRevenue = number.subtract(new BigInteger("1"));
            }else {
                startRevenue = number.add(new BigInteger("1"));
                endRevenue = upperLimiter;
            }
        }else {
            if (helper.isLowerValue(Type.REVENUE,number,0,0)){
                startRevenue = number.add(new BigInteger("1"));
                endRevenue = upperLimiter;
            }else{
                startRevenue = lowerLimiter;
                endRevenue = number.subtract(new BigInteger("1"));
            }
        }
        List<Movie> movies = repository.findByRevenueBetween(startRevenue, endRevenue);
        return movies.get(random.nextInt(movies.size()));
    }
    public Movie getByVoteAverage(int number, String type){
        Random random = new Random();
        int upperLimiter;
        int lowerLimiter;
        int startVoteAverage;
        int endVoteAverage;
        if (Objects.equals(type, "Easy")){
            upperLimiter = voteUpperLimit;
            lowerLimiter = voteLowerLimit;
        }else{
            upperLimiter = number+2;
            lowerLimiter = number-2;
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.VOTE,new BigInteger("0"),number,0)){
                startVoteAverage = lowerLimiter;
                endVoteAverage = number-1;
            }else{
                startVoteAverage = number+1;
                endVoteAverage = upperLimiter;
            }
        }else {
            if (helper.isLowerValue(Type.VOTE,new BigInteger("0"),number,0)){
                startVoteAverage = number+1;
                endVoteAverage = upperLimiter;
            }else {
                startVoteAverage = lowerLimiter;
                endVoteAverage = number-1;
            }
        }
        List<Movie> movies = repository.findByVoteAverageBetween(startVoteAverage, endVoteAverage);
        return movies.get(random.nextInt(movies.size()));
    }
    public Movie getByRuntime(int number, String type){
        Random random = new Random();
        int upperLimiter;
        int lowerLimiter;
        int startRuntime;
        int endRuntime;
        if (Objects.equals(type, "Easy")){
            upperLimiter = runtimeUpperLimit;
            lowerLimiter = runtimeLowerLimit;
        }else{
            upperLimiter = number+20;
            lowerLimiter = number-20;
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                startRuntime = lowerLimiter;
                endRuntime = number-1;
            }else{
                startRuntime = number+1;
                endRuntime = upperLimiter;
            }
        }else {
            if (helper.isLowerValue(Type.RUNTIME,new BigInteger("0"),number,0)){
                startRuntime = number+1;
                endRuntime = upperLimiter;
            }else {
                startRuntime = lowerLimiter;
                endRuntime = number-1;
            }
        }
        List<Movie> movies = repository.findByRuntimeBetween(startRuntime, endRuntime);
        return movies.get(random.nextInt(movies.size()));
    }
    public Movie getByPopularity(double number, String type){
        Random random = new Random();
        double upperLimiter;
        double lowerLimiter;
        double startPopularity;
        double endPopularity;

        if (Objects.equals(type, "Easy")){
            upperLimiter = popularityUpperLimit;
            lowerLimiter = popularityLowerLimit;
        }else{
            upperLimiter = number+5;
            lowerLimiter = number-5;
        }
        if (helper.isHigher()){
            if (helper.isUpperValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                startPopularity = lowerLimiter;
                endPopularity = number-0.001;
            }else{
                startPopularity = number+0.001;
                endPopularity = upperLimiter;
            }
        }else {
            if (helper.isLowerValue(Type.POPULARITY,new BigInteger("0"),0,number)){
                startPopularity = number+0.001;
                endPopularity = upperLimiter;
            }else {
                startPopularity = lowerLimiter;
                endPopularity = number-0.001;
            }
        }
        List<Movie> movies = repository.findByPopularityBetween(startPopularity, endPopularity);

        return movies.get(random.nextInt(movies.size()));
    }
}

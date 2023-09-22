package com.movie.game.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MovieHelper {
    public BigInteger getId(){
        BigInteger maxLimit = new BigInteger("4798");
        BigInteger minLimit = new BigInteger("1");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
        return res;
    }
    public boolean isHigher(){
        Random random = new Random();
        return random.nextInt(2 - 1 + 1) + 1 == 1;
    }

    public boolean isUpperValue(Type type, BigInteger revenue, int smallNumber, double popularity){
        return switch (type) {
            case VOTE -> smallNumber == 10;
            case RUNTIME -> smallNumber == 338;
            case REVENUE -> Objects.equals(revenue, new BigInteger("2787965087"));
            case POPULARITY -> popularity == 875.5813;
        };
    }

    public boolean isLowerValue(Type type, BigInteger revenue, int smallNumber, double popularity){
        return switch (type) {
            case VOTE, RUNTIME -> smallNumber == 0;
            case REVENUE -> Objects.equals(revenue, new BigInteger("0"));
            case POPULARITY -> popularity == 0.000372;
        };
    }
}

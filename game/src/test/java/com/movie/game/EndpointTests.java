package com.movie.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.movie.game.model.Movie;
import com.movie.game.model.Score;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.in;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = {"spring.main.allow-bean-definition-overriding=true", "server.servlet.context-path=/"})
public class EndpointTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void movieEndpointReturnsMovie(){
        given().get("/game").then().statusCode(200).extract().as(Movie.class);
    }

    @Test
    public void movieRevenueEndpointReturnsMovie(){
        var test = given().get("/game").then().statusCode(200).extract().as(Movie.class);
        Map<String, Object> params = new HashMap<>();
        params.put("number", test.getRevenue());
        params.put("type", "Easy");
        Movie movie = given().params(params).get("/game/revenue").then().extract().as(Movie.class);
        assertThat(movie.getRevenue() != null);

    }

    @Test
    public void movieVoteAverageEndpointReturnsMovie(){
        var test = given().get("/game").then().statusCode(200).extract().as(Movie.class);
        Map<String, Object> params = new HashMap<>();
        params.put("number", test.getVoteAverage());
        params.put("type", "Easy");
        given().params(params).get("/game/vote").then().statusCode(200);
    }

    @Test
    public void movieRuntimeEndpointReturnsMovie(){
        var test = given().get("/game").then().statusCode(200).extract().as(Movie.class);
        Map<String, Object> params = new HashMap<>();
        params.put("number", test.getRuntime());
        params.put("type", "Easy");
        given().params(params).get("/game/runtime").then().statusCode(200);
    }

    @Test
    public void moviePopularityEndpointReturnsMovie(){
        var test = given().get("/game").then().statusCode(200).extract().as(Movie.class);
        Map<String, Object> params = new HashMap<>();
        params.put("number", test.getPopularity());
        params.put("type", "Easy");
        Movie movie = given().params(params).get("/game/popularity").then().extract().as(Movie.class);
        assertThat(movie.getPopularity() != 0.0);
    }

    @Test
    public void createScoreReturnsObjectWithId() throws JsonProcessingException {
        var score = new Score();
        score.setPoints(20);
        score.setDifficulty("Easy");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(score);
        var response = given().header("Content-Type", "application/json").body(body).post("/game/score").then().statusCode(200).extract().as(Score.class);
        assertThat(response.getId() != null);
    }

    @Test
    public void getTopScoreOnEasyReturnHighestScore() throws JsonProcessingException {
        var score = new Score();
        var score2 = new Score();
        score.setPoints(0);
        score.setDifficulty("Easy");
        score2.setPoints(1);
        score2.setDifficulty("Easy");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        given().header("Content-Type", "application/json").body(ow.writeValueAsString(score)).post("/game/score").then().statusCode(200);
        given().header("Content-Type", "application/json").body(ow.writeValueAsString(score2)).post("/game/score").then().statusCode(200);
        var highScore = given().get("/game/score/easy").then().statusCode(200).extract().as(int.class);
        assertThat(highScore == 1);
    }

    @Test
    public void getTopScoreOnHardReturnHighestScore() throws JsonProcessingException {
        var score = new Score();
        var score2 = new Score();
        score.setPoints(0);
        score.setDifficulty("Hard");
        score2.setPoints(1);
        score2.setDifficulty("Hard");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        given().header("Content-Type", "application/json").body(ow.writeValueAsString(score)).post("/game/score").then().statusCode(200);
        given().header("Content-Type", "application/json").body(ow.writeValueAsString(score2)).post("/game/score").then().statusCode(200);
        var highScore = given().get("/game/score/hard").then().statusCode(200).extract().as(int.class);
        assertThat(highScore == 1);
    }
}

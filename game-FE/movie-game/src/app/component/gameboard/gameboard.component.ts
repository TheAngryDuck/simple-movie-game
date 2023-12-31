import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ConfigService } from 'src/app/config/config.service';
import { movie } from 'src/app/type/movie';

@Component({
  selector: 'app-gameboard',
  templateUrl: './gameboard.component.html',
  styleUrls: ['./gameboard.component.css'],
  providers: [ ConfigService ]
})
export class GameboardComponent {
  score: number = 0;
  movieLeft: movie | undefined;
  movieRight: movie | undefined;
  type: string = '';
  difficulty: string = '';


  constructor(
    private route: ActivatedRoute,
    private configService: ConfigService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.type = String(this.route.snapshot.paramMap.get('type'));
    this.difficulty = String(this.route.snapshot.paramMap.get('difficulty'));
    this.configService.getMovie().subscribe((data: movie) => {
      this.movieLeft = data;
      this.populateRight();
    })
  }

  getByRevenue(type: string){
    this.configService.getMovieByRevenue(Number(this.movieLeft?.revenue),type).subscribe((data: movie) => {
          this.movieRight = data;
        })
  }

  getByVote(type: string){
      this.configService.getMovieByVote(Number(this.movieLeft?.voteAverage),type).subscribe((data: movie) => {
          this.movieRight = data;
      })
  }

  getByRuntime(type: string){
      this.configService.getMovieByRuntime(Number(this.movieLeft?.runtime),type).subscribe((data: movie) => {
          this.movieRight = data;
      })
  }

  getByPopularity(type: string){
      this.configService.getMovieByPopularity(Number(this.movieLeft?.popularity),type).subscribe((data: movie) => {
          this.movieRight = data;
      })
  }

  populateRight() {
      switch(this.type){
        case 'Revenue': {
          this.getByRevenue(this.difficulty);
          break;
        }
        case 'Vote average':{
          this.getByVote(this.difficulty);
          break;
        }
        case 'Runtime':{
          this.getByRuntime(this.difficulty);
          break;
        }
        case 'Popularity':{
          this.getByPopularity(this.difficulty);
          break;
        }
      }
  }

  CorrectAnswer(){
    this.score = this.score +1;
    this.movieLeft = this.movieRight;
    this.populateRight();
  }

  WrongAnswer(){
    let score = {
      points: this.score,
      difficulty: this.difficulty
    }
    this.configService.createNewScore(score).subscribe(() => {});
    this.location.back();
  }

  Higher(){
    switch(this.type){
      case 'Revenue': {
        if(Number(this.movieLeft?.revenue) < Number(this.movieRight?.revenue)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
      case 'Vote average':{
        if(Number(this.movieLeft?.voteAverage) < Number(this.movieRight?.voteAverage)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
      case 'Runtime':{
        if(Number(this.movieLeft?.runtime) < Number(this.movieRight?.runtime)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
      case 'Popularity':{
        if(Number(this.movieLeft?.popularity) < Number(this.movieRight?.popularity)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
    }
  }

  Lower(){
    switch(this.type){
      case 'Revenue': {
        if(Number(this.movieLeft?.revenue) > Number(this.movieRight?.revenue)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
      case 'Vote average':{
        if(Number(this.movieLeft?.voteAverage) > Number(this.movieRight?.voteAverage)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
      case 'Runtime':{
        if(Number(this.movieLeft?.runtime) > Number(this.movieRight?.runtime)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
      case 'Popularity':{
        if(Number(this.movieLeft?.popularity) > Number(this.movieRight?.popularity)){
          this.CorrectAnswer();
        }else{
          this.WrongAnswer();
        }
        break;
      }
    }
  }
}

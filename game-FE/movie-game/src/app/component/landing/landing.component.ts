import { Component } from '@angular/core';
import {MatRadioModule} from '@angular/material/radio';
import {NgFor} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import { ConfigService } from 'src/app/config/config.service';


@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
  providers: [ ConfigService ]
})

export class LandingComponent {
  scoreEasy: number = 0;
  scoreHard: number = 0;
  gameType: string = "";
  gameDifficulty: string = ""
  gameTypes: string[] = ['Revenue', 'Vote average', 'Runtime', 'Popularity'];
  gameDifficulties: string[] = ['Easy', 'Hard'];

  constructor(
    private configService: ConfigService
  ) {}

  ngOnInit(): void {
    this.configService.getTopScoreEasy().subscribe((data: number) => {
      this.scoreEasy = data;
    });

    this.configService.getTopScoreHard().subscribe((data: number) => {
      if(data > 0){
        this.scoreHard = data;
      }
    });
  }
}

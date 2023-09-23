import { Component } from '@angular/core';
import {MatRadioModule} from '@angular/material/radio';
import {NgFor} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';


@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})

export class LandingComponent {
  gameType: string = "";
  gameDifficulty: string = ""
  gameTypes: string[] = ['Revenue', 'Vote average', 'Runtime', 'Popularity'];
  gameDifficulties: string[] = ['Easy', 'Hard'];

}

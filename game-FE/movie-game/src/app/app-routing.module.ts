import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameboardComponent } from './component/gameboard/gameboard.component';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './component/landing/landing.component';

const routes: Routes = [
  { path: 'game/:type/:difficulty', component: GameboardComponent },
  { path: '', component: LandingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

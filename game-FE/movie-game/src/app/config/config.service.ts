import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { movie } from '../type/movie';
import { score } from '../type/score';

const reqHeaders = new HttpHeaders().set('Content-Type', 'application/json').set('Accept', 'application/json')

@Injectable()
export class ConfigService {
  configUrl = 'http://localhost:8080/game';

  constructor(private http: HttpClient) { }

  getMovie() {
    return this.http.get<movie>(this.configUrl);
  }

  getMovieByPopularity(popularity: number, type: string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",popularity);
    queryParams = queryParams.append("type",type);
    return this.http.get<movie>(this.configUrl+"/popularity",{params:queryParams});
  }

  getMovieByRuntime(runtime: number, type: string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",runtime);
    queryParams = queryParams.append("type",type);
    return this.http.get<movie>(this.configUrl+"/runtime",{params:queryParams});
  }

  getMovieByVote(vote: number, type: string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",vote);
    queryParams = queryParams.append("type",type);
    return this.http.get<movie>(this.configUrl+"/vote",{params:queryParams});
  }

  getMovieByRevenue(revenue: number, type: string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",revenue);
    queryParams = queryParams.append("type",type);
    return this.http.get<movie>(this.configUrl+"/revenue",{params:queryParams});
  }

  getTopScoreHard(){
    return this.http.get<number>(this.configUrl+"/score/hard");
  }

  getTopScoreEasy(){
    return this.http.get<number>(this.configUrl+"/score/easy");
  }

  createNewScore(score: score){
    return this.http.post<score>(this.configUrl+"/score", score, {headers: reqHeaders})
  }
}



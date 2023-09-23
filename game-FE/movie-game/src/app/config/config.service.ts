import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { movie } from '../type/movie';

const reqHeaders = new HttpHeaders().set('Content-Type', 'application/json').set('Accept', 'application/json')

@Injectable()
export class ConfigService {
  configUrl = 'http://localhost:8080/game';

  constructor(private http: HttpClient) { }

  getMovie() {
    return this.http.get<movie>(this.configUrl);
  }

  getMovieByPopularity(popularity: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",popularity);
    return this.http.get<movie>(this.configUrl+"/popularity",{params:queryParams});
  }

  getMovieByRuntime(runtime: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",runtime);
    return this.http.get<movie>(this.configUrl+"/runtime",{params:queryParams});
  }

  getMovieByVote(vote: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",vote);
    return this.http.get<movie>(this.configUrl+"/vote",{params:queryParams});
  }

  getMovieByRevenue(revenue: number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("number",revenue);
    return this.http.get<movie>(this.configUrl+"/revenue",{params:queryParams});
  }
}



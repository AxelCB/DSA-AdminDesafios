import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs";
import {Challenge} from "./challenge";
import {environment} from "../../environments/environment";

@Injectable()
export class ChallengeService {

  constructor(private http: Http) { }

  getChallenges(): Observable<Challenge[]> {
    return this.http.get(environment.backendUrl+"/challenges")
      .map(response => response.json() as Challenge[])
      .catch(this.handleError);
  }

  getChallenge(id: number): Observable<Challenge> {
    if (! isNaN(id)) {
      return this.http.get(environment.backendUrl+"/challenges/"+id)
        .map(response => response.json() as Challenge)
        .catch(this.handleError)
    } else {
      return Observable.of<Challenge>();
    }

  }

  update(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(environment.backendUrl+"/challenges/"+challenge.id,JSON.stringify(challenge),options)
      .catch(this.handleError);
  }

  create(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl+"/challenges/",JSON.stringify(challenge),options)
      .catch(this.handleError);
  }

  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}

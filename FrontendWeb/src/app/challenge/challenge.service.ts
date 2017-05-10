import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs';
import {Challenge} from './challenge';
import {environment} from '../../environments/environment';
import {MessagesService} from '../alert-messages/alert-messages.service';
import {FORBIDDEN, INTERNAL_SERVER_ERROR, NOT_FOUND, UNAUTHORIZED} from 'http-status-codes';
import {Message} from '../alert-messages/message';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Injectable()
export class ChallengeService {

  constructor(private http: Http, private messagesService: MessagesService, private authService: AuthService, private router: Router) { }

  getChallenges(): Observable<Challenge[]> {
    let headers = new Headers({ 'Authorization': this.authService.getToken()});
    let options = new RequestOptions({ headers: headers });
    return this.http.get(environment.backendUrl + '/challenges', options)
      .map(response => response.json() as Challenge[])
      .catch((error) => this.handleError(error, this.messagesService));
  }

  getChallenge(id: number): Observable<Challenge> {
    if (! isNaN(id)) {
      let headers = new Headers({ 'Authorization': this.authService.getToken()});
      let options = new RequestOptions({ headers: headers });
      return this.http.get(environment.backendUrl + '/challenges/' + id, options)
        .map(response => response.json() as Challenge)
        .catch((error) => this.handleError(error, this.messagesService));
    } else {
      return Observable.of<Challenge>();
    }

  }

  update(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(environment.backendUrl + '/challenges/' + challenge.id, JSON.stringify(challenge), options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  create(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl + '/challenges/', JSON.stringify(challenge), options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  delete(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.delete(environment.backendUrl + '/challenges/' + challenge.id, options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  private handleError (error: Response | any, messagesService: MessagesService) {
    let errorMessage = new Message();
    errorMessage.isError = true;
    if ((<Response>error).status != null) {
      let responseError = <Response>error;
      errorMessage.responseCode = responseError.status;
      switch (responseError.status) {
        case NOT_FOUND: {
          errorMessage.content = 'El desafío seleccionado no pudo ser encontrado';
          break;
        }
        case INTERNAL_SERVER_ERROR: {
          errorMessage.content = 'Ha ocurrido un error inesperado. Intente nuevamente más tarde, o vuelva al inicio';
          break;
        }
        case UNAUTHORIZED:
        case FORBIDDEN:
          if (this.authService.getToken() != null) {
            this.authService.logout().subscribe();
          } else {
            localStorage.removeItem('loggedUser');
            this.router.navigate(['/login']);
          }
          this.messagesService.sendMessage(new Message("Su sesión ha caducado, inicie sesión nuevamente.",true));
          break;
      }
    } else {
      errorMessage.content = error.message ? error.message : error.toString();
    }
    messagesService.sendMessage(errorMessage);
    return Observable.throw(errorMessage);
  }

}

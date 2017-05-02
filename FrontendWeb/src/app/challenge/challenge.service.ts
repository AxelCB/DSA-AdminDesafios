import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs';
import {Challenge} from './challenge';
import {environment} from '../../environments/environment';
import {MessagesService} from '../alert-messages/alert-messages.service';
import {INTERNAL_SERVER_ERROR, NOT_FOUND} from 'http-status-codes';
import {Message} from '../alert-messages/message';

@Injectable()
export class ChallengeService {

  constructor(private http: Http, private messagesService: MessagesService) { }

  getChallenges(): Observable<Challenge[]> {
    return this.http.get(environment.backendUrl + '/challenges')
      .map(response => response.json() as Challenge[])
      .catch((error) => this.handleError(error, this.messagesService));
  }

  getChallenge(id: number): Observable<Challenge> {
    if (! isNaN(id)) {
      return this.http.get(environment.backendUrl + '/challenges/' + id)
        .map(response => response.json() as Challenge)
        .catch((error) => this.handleError(error, this.messagesService));
    } else {
      return Observable.of<Challenge>();
    }

  }

  update(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(environment.backendUrl + '/challenges/' + challenge.id, JSON.stringify(challenge), options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  create(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl + '/challenges/', JSON.stringify(challenge), options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  delete(challenge: Challenge): Observable<Challenge> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.delete(environment.backendUrl + '/challenges/' + challenge.id, options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  private handleError (error: Response | any, messagesService: MessagesService) {
    let errorMessage = new Message();
    errorMessage.isError = true;
    if (error instanceof Response) {
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
      }
    } else {
      errorMessage.content = error.message ? error.message : error.toString();
    }
    messagesService.sendMessage(errorMessage);
    return Observable.throw(errorMessage);
  }

  uploadChallengeFile(file: File): Observable<string>{
    let formData:FormData = new FormData();
    formData.append('uploadFile', file, file.name);
    let headers = new Headers();
    //headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    let options = new RequestOptions({ headers: headers });

    return this.http.post(environment.backendUrl+"/challenges/files", formData, options)
      .map(res => res.text())
      .catch(error => Observable.throw(error));
  }

}

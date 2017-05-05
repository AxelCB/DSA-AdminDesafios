import { Injectable } from '@angular/core';
import {User} from './user';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';
import {MessagesService} from '../alert-messages/alert-messages.service';
import {Message} from '../alert-messages/message';
import {INTERNAL_SERVER_ERROR, NOT_FOUND} from 'http-status-codes';
import { Http, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class AuthService {

  constructor(private http: Http, private messagesService: MessagesService) {
  }

  login(user: User): Observable<boolean> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl  + '/auth/authenticate', JSON.stringify(user), options)
      .map(response => {
        if (response != null) {
          let loggedUser = response.json() as User;
          if (loggedUser.token) {
            loggedUser.password = null;
            localStorage.setItem('loggedUser', JSON.stringify(loggedUser));
            return true;
          }
        }
        return false;
      })
      .catch((error) => this.handleError(error, this.messagesService));
  }

  logout(): Observable<boolean> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(environment.backendUrl  + '/auth/logout', options)
      .map(() => {
        localStorage.removeItem('loggedUser');
        return true;
      })
      .catch((error) => this.handleError(error, this.messagesService));
  }

  private handleError (error: Response | any, messagesService: MessagesService) {
    let errorMessage = new Message();
    errorMessage.isError = true;
    if (error instanceof Response) {
      let responseError = <Response>error;
      errorMessage.responseCode = responseError.status;
      switch (responseError.status) {
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
}

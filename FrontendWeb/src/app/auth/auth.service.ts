import { Injectable } from '@angular/core';
import {User} from './user';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';
import {MessagesService} from '../alert-messages/alert-messages.service';
import {Message} from '../alert-messages/message';
import {FORBIDDEN, INTERNAL_SERVER_ERROR, NOT_FOUND, UNAUTHORIZED} from 'http-status-codes';
import { Http, Headers, RequestOptions } from '@angular/http';
import {Router} from '@angular/router';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class AuthService {

  // Observable boolean sources
  private loggedInStatusChangedSource = new Subject<boolean>();

  // Observable boolean streams
  loggedInStatusChanged$ = this.loggedInStatusChangedSource.asObservable();

  constructor(private http: Http, private messagesService: MessagesService, private router: Router) {
  }

  updateLoggedInStatus(newStatus: boolean) {
    this.loggedInStatusChangedSource.next(newStatus);
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

  validateSession(): Observable<boolean> {
    let headers = new Headers({ 'Authorization': this.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(environment.backendUrl  + '/auth/validate', options)
      .map(() => {
        return true;
      })
      .catch((error) => this.handleError(error, this.messagesService));
  }

  logout(): Observable<boolean> {
    let headers = new Headers({ 'Authorization': this.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(environment.backendUrl  + '/auth/logout', options)
      .map(() => {
        localStorage.removeItem('loggedUser');
        this.router.navigate(['/login']);
        return true;
      })
      .catch((error) => this.handleError(error, this.messagesService));
  }

  getToken(): string {
    let loggedUser = <User>JSON.parse(localStorage.getItem('loggedUser'));
    if (loggedUser != null) {
      return loggedUser.token;
    } else {
      return null;
    }
  }

  private handleError (error: Response | any, messagesService: MessagesService) {
    let errorMessage = new Message();
    errorMessage.isError = true;
    if ((<Response>error).status != null) {
      let responseError = <Response>error;
      errorMessage.responseCode = responseError.status;
      switch (responseError.status) {
        case UNAUTHORIZED:
        case FORBIDDEN:
        case NOT_FOUND:
          if (this.getToken() != null) {
            this.logout().subscribe();
          } else {
            localStorage.removeItem('loggedUser');
            this.router.navigate(['/login']);
          }
          break;
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

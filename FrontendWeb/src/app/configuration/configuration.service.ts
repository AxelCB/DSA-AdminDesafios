import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {Configuration} from './configuration';
import {environment} from '../../environments/environment';
import {MessagesService} from '../alert-messages/alert-messages.service';
import {Message, MessagePriority} from '../alert-messages/message';
import {FORBIDDEN, INTERNAL_SERVER_ERROR, NOT_FOUND, UNAUTHORIZED} from 'http-status-codes';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Injectable()
export class ConfigurationService {

  constructor(private messagesService: MessagesService, private http: Http, private authService: AuthService, private router: Router) { }

  getConfigurations(): Observable<Configuration[]> {
    let headers = new Headers({ 'Authorization': this.authService.getToken()});
    let options = new RequestOptions({ headers: headers });
    return this.http.get(environment.backendUrl + '/configurations', options)
      .map(response => response.json() as Configuration[])
      .catch((error) => this.handleError(error, this.messagesService));
  }

  getConfiguration(id: number): Observable<Configuration> {
    if (! isNaN(id)) {
      let headers = new Headers({ 'Authorization': this.authService.getToken()});
      let options = new RequestOptions({ headers: headers });
      return this.http.get(environment.backendUrl + '/configurations/' + id, options)
        .map(response => response.json() as Configuration)
        .catch((error) => this.handleError(error, this.messagesService));
    } else {
      return Observable.of<Configuration>();
    }

  }

  update(configuration: Configuration): Observable<Configuration> {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(environment.backendUrl + '/configurations/' + configuration.id, JSON.stringify(configuration), options)
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
          errorMessage.content = 'La configuración seleccionada no pudo ser encontrada';
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
          this.messagesService.sendMessage(new Message('Su sesión ha caducado, inicie sesión nuevamente.', true, responseError.status ,MessagePriority.HIGH));
          break;
      }
    } else {
      errorMessage.content = error.message ? error.message : error.toString();
    }
    messagesService.sendMessage(errorMessage);
    return Observable.throw(errorMessage);
  }
}

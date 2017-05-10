/**
 * Created by acollard on 1/1/17.
 */

import { Injectable } from '@angular/core';
import {Category} from './category';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {MessagesService} from '../alert-messages/alert-messages.service';
import {Message} from '../alert-messages/message';
import {FORBIDDEN, INTERNAL_SERVER_ERROR, NOT_FOUND, UNAUTHORIZED} from 'http-status-codes';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Injectable()
export class CategoryService {

  constructor(private http: Http, private messagesService: MessagesService, private authService: AuthService, private router: Router) { }

  getCategories(): Observable<Category[]> {
      let headers = new Headers({ 'Authorization': this.authService.getToken()});
      let options = new RequestOptions({ headers: headers });
      return this.http.get(environment.backendUrl  + '/categories', options)
                  .map(response => response.json() as Category[])
                  .catch((error) => this.handleError(error, this.messagesService));
  }

  getCategory(id: number): Observable<Category> {
    let headers = new Headers({ 'Authorization': this.authService.getToken()});
    let options = new RequestOptions({ headers: headers });
      if (! isNaN(id)) {
          return this.http.get(environment.backendUrl + '/categories/'  + id, options)
              .map(response => response.json() as Category)
              .catch((error) => this.handleError(error, this.messagesService));
      } else {
          return Observable.of<Category>();
      }

  }

  update(category: Category): Observable<Category> {
      let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
      let options = new RequestOptions({ headers: headers });
      return this.http.put(environment.backendUrl + '/categories/' + category.id, JSON.stringify(category), options)
          .catch((error) => this.handleError(error, this.messagesService));
  }

  delete(category: Category): Observable<Category> {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.delete(environment.backendUrl + '/categories/' + category.id, options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  create(category: Category): Observable<Category> {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': this.authService.getToken() });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl + '/categories/', JSON.stringify(category), options)
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
          errorMessage.content = 'La categoría seleccionada no pudo ser encontrada';
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

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
import {INTERNAL_SERVER_ERROR, NOT_FOUND} from 'http-status-codes';

@Injectable()
export class CategoryService {

  constructor(private http: Http, private messagesService: MessagesService) { }

  getCategories(): Observable<Category[]> {
      return this.http.get(environment.backendUrl  + '/categories')
                  .map(response => response.json() as Category[])
                  .catch((error) => this.handleError(error, this.messagesService));
  }

  getCategory(id: number): Observable<Category> {
      if (! isNaN(id)) {
          return this.http.get(environment.backendUrl + '/categories/'  + id)
              .map(response => response.json() as Category)
              .catch((error) => this.handleError(error, this.messagesService));
      } else {
          return Observable.of<Category>();
      }

  }

  update(category: Category): Observable<Category> {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      return this.http.put(environment.backendUrl + '/categories/' + category.id, JSON.stringify(category), options)
          .catch((error) => this.handleError(error, this.messagesService));
  }

  delete(category: Category): Observable<Category> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.delete(environment.backendUrl + '/categories/' + category.id, options)
      .catch((error) => this.handleError(error, this.messagesService));
  }

  create(category: Category): Observable<Category> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl + '/categories/', JSON.stringify(category), options)
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
          errorMessage.content = 'La categoría seleccionada no pudo ser encontrada';
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
}

/**
 * Created by acollard on 1/1/17.
 */

import { Injectable } from '@angular/core';
import {Category} from "./category";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable()
export class CategoryService {

  constructor(private http: Http) { }

  getCategories(): Observable<Category[]> {
      return this.http.get(environment.backendUrl+"/categories")
                  .map(response => response.json() as Category[])
                  .catch(this.handleError);
  }

  getCategory(id: number): Observable<Category> {
      if (! isNaN(id)) {
          return this.http.get(environment.backendUrl+"/categories/"+id)
              .map(response => response.json() as Category)
              .catch(this.handleError)
      } else {
          return Observable.of<Category>();
      }

  }

  update(category: Category): Observable<Category> {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      return this.http.put(environment.backendUrl+"/categories/"+category.id,JSON.stringify(category),options)
          .catch(this.handleError);
  }

  delete(category: Category): Observable<Category> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.delete(environment.backendUrl+"/categories/"+category.id,options)
      .catch(this.handleError);
  }

  create(category: Category): Observable<Category> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(environment.backendUrl+"/categories/",JSON.stringify(category),options)
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

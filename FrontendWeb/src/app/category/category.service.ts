/**
 * Created by acollard on 1/1/17.
 */

import { Injectable } from '@angular/core';
import {Category} from "./category";
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {environment} from "../../environments/environment";

@Injectable()
export class CategoryService {

  constructor(private http: Http) { }

  getCategories(): Promise<Category[]> {
      return this.http.get(environment.backendUrl+"/categories")
                  .toPromise()
                  .then(response => response.json() as Category[])
                  .catch(this.handleError);
  }

  getCategory(id: number): Promise<Category> {
      if (! isNaN(id)) {
          return this.http.get(environment.backendUrl+"/categories/"+id)
            .toPromise()
            .then(response => response.json() as Category)
            .catch(this.handleError);
      } else {
          return Promise.resolve(new Category());
      }

  }

  update(category: Category): void {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      this.http.put(environment.backendUrl+"/categories/"+category.id,category,options)
        .toPromise().then().catch(this.handleError);
  }

  create(category: Category): void {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    this.http.post(environment.backendUrl+"/categories/",category,options)
      .toPromise().then().catch(this.handleError);

  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

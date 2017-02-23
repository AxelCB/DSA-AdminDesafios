/**
 * Created by acollard on 1/1/17.
 */

import { Injectable } from '@angular/core';
import {Category} from "./category";
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CategoryService {

  constructor(private http: Http) { }

  getCategories(): Promise<Category[]> {
      return this.http.get("http://localhost:8080/api/categories")
                  .toPromise()
                  .then(response => response.json().data as Category[])
                  .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}

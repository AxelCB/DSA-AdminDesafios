/**
 * Created by acollard on 1/1/17.
 */

import { Injectable } from '@angular/core';
import {Category} from "./category";
import {CATEGORIES} from "./mock-categories";

@Injectable()
export class CategoryService {
  getCategories(): Promise<Category[]> {
      return Promise.resolve(CATEGORIES);
  }
}

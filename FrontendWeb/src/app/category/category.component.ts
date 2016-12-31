/**
 * Created by acollard on 31/12/16.
 */

import { Component } from '@angular/core';
import { Category } from './category';

@Component({
  selector: 'category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent {
  title = 'List of Categories';
  categories = [Category];
}

/**
 * Created by acollard on 31/12/16.
 */

import {Component, OnInit} from '@angular/core';
import { Category } from '../category';
import {CategoryService} from "../category.service";

@Component({
  moduleId: module.id,
  selector: 'category-list',
  templateUrl: 'category-list.component.html',
  styleUrls: ['category-list.component.css'],
  providers: [CategoryService]
})

export class CategoryListComponent implements OnInit {
  categories : Category[];

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories():void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }
}

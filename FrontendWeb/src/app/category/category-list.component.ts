/**
 * Created by acollard on 31/12/16.
 */

import {Component, OnInit} from '@angular/core';
import { Category } from './category';
import {CategoryService} from "./category.service";

@Component({
  selector: 'category-list',
  templateUrl: './category-list.html',
  styleUrls: ['./category-list.css'],
  providers: [CategoryService]
})

export class CategoryListComponent implements OnInit {
  categories : Category[];
  selectedCategory : Category;

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories():void {
    this.categoryService.getCategories().then(categories => this.categories = categories);
  }

  onSelect(category : Category):void {
    this.selectedCategory=category;
  }
}
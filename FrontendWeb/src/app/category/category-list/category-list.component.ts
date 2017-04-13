/**
 * Created by acollard on 31/12/16.
 */

import {Component, OnInit} from '@angular/core';
import { Category } from '../category';
import {CategoryService} from '../category.service';
import {DialogConfirmDeleteComponent} from '../../dialog-confirm-delete/dialog-confirm-delete.component';

@Component({
  moduleId: module.id,
  selector: 'category-list',
  templateUrl: 'category-list.component.html',
  styleUrls: ['category-list.component.css'],
  providers: [CategoryService]
})

export class CategoryListComponent implements OnInit {
  categories: Category[];
  deleteConfirmationDialog: DialogConfirmDeleteComponent;

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }

  delete(category: Category): void {
    this.deleteConfirmationDialog = new DialogConfirmDeleteComponent(() => {
        this.categoryService.delete(category).subscribe(() => this.getCategories()); });
    this.deleteConfirmationDialog.open();
  }
}

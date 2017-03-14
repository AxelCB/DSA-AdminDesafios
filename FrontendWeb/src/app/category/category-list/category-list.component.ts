/**
 * Created by acollard on 31/12/16.
 */

import {Component, OnInit} from '@angular/core';
import { Category } from '../category';
import {CategoryService} from "../category.service";
import {MdDialog} from "@angular/material";
import {DialogConfirmDeleteComponent} from "../../dialog-confirm-delete/dialog-confirm-delete.component";

@Component({
  moduleId: module.id,
  selector: 'category-list',
  templateUrl: 'category-list.component.html',
  styleUrls: ['category-list.component.css'],
  providers: [CategoryService]
})

export class CategoryListComponent implements OnInit {
  categories : Category[];


  constructor(private categoryService: CategoryService, public dialog: MdDialog) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories():void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }

  delete(category: Category): void {
    let dialogRef = this.dialog.open(DialogConfirmDeleteComponent);
    dialogRef.afterClosed().subscribe(result => {
      if ( result ) {
        this.categoryService.delete(category).subscribe(()=> this.getCategories());
      }
    });
  }
}

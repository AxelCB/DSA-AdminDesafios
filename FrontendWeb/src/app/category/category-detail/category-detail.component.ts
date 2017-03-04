import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../category.service";
import {Category} from "../category";
import {ActivatedRoute, Params} from "@angular/router";
import { Location } from '@angular/common';
import {isNullOrUndefined} from "util";

@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryDetailComponent implements OnInit {
  category: Category;

  constructor(private categoryService: CategoryService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    //only do this if there's an id as param
    this.route.params
      .switchMap((params: Params) => this.categoryService.getCategory(+params['id']))
      .subscribe(category => this.category = category);

  }

  save() {
      this.category.name = this.category.name.trim();
      if ( this.category.name.length > 0) {
          if ( isNullOrUndefined( this.category.id ) ) {
              this.categoryService.create(this.category);
          } else {
              this.categoryService.update(this.category);
          }
          //if succesful go back to list
      }
  }

  cancel() {
      //go back to list
  }
}

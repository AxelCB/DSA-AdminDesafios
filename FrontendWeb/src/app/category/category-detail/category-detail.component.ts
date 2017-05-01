import { Component, OnInit } from '@angular/core';
import {CategoryService} from '../category.service';
import {Category} from '../category';
import {ActivatedRoute, Params} from '@angular/router';
import { Location } from '@angular/common';
import {isNullOrUndefined} from 'util';

@Component({
  moduleId: module.id,
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryDetailComponent implements OnInit {
  category: Category = new Category();

  constructor(private categoryService: CategoryService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.categoryService.getCategory(+params['id']))
      .subscribe(category => this.category = category);
  }

  goBack() {
      this.location.back();
  }

  save() {
      this.category.name = this.category.name.trim();
      if ( this.category.name ) {
          if ( isNullOrUndefined( this.category.id ) ) {
              this.categoryService.create(this.category).subscribe(() => this.goBack());
          } else {
            this.categoryService.update(this.category).subscribe(() => this.goBack());
          }
      }
  }

  cancel() {
      this.goBack();
  }
}

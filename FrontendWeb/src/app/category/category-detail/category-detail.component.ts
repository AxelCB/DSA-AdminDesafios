import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../category.service";
import {Category} from "../category";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  selector: 'app-category-detail',
  templateUrl: 'category-detail.component.html',
  styleUrls: ['category-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryDetailComponent implements OnInit {
  category: Category;

  constructor(private categoryService: CategoryService,private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.categoryService.getCategory(+params['id']))
      .subscribe(category => this.category = category);

  }

  getCategory(id: number):void {
    this.categoryService.getCategory(id).then(category => this.category = category);
  }

}

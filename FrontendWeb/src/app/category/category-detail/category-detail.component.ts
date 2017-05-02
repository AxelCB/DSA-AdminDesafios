import { Component, OnInit } from '@angular/core';
import {CategoryService} from '../category.service';
import {Category} from '../category';
import {ActivatedRoute, Params, Router} from '@angular/router';
import { Location } from '@angular/common';
import {isNullOrUndefined} from 'util';
import {Message} from '../../alert-messages/message';
import {NOT_FOUND} from 'http-status-codes';
import {MessagesService} from '../../alert-messages/alert-messages.service';

@Component({
  moduleId: module.id,
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryDetailComponent implements OnInit {
  category: Category = new Category();

  constructor(private categoryService: CategoryService, private route: ActivatedRoute,
              private location: Location, private router: Router, private messagesService: MessagesService) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.categoryService.getCategory(+params['id']))
      .subscribe(category => this.category = category,
        error => {
          if ((<Message>error).responseCode != null && (<Message>error).responseCode === NOT_FOUND) {
            this.router.navigate(['/categories']);
          }
        });
  }

  goBack() {
      this.location.back();
  }

  save() {
      this.category.name = this.category.name.trim();
      if ( this.category.name ) {
          if ( isNullOrUndefined( this.category.id ) ) {
            this.categoryService.create(this.category).subscribe(
              () => {
                this.goBack();
                this.messagesService.sendMessage(new Message('Categoría creada correctamente', false));
              },
              () => {
                this.messagesService.appendToMessage('No se pudo crear la categoría');
              }
            );
          } else {
            this.categoryService.update(this.category).subscribe(
              () => {
                this.goBack();
                this.messagesService.sendMessage(new Message('Categoría actualizada correctamente', false));
              },
              () => {
                this.messagesService.appendToMessage('No se pudo actualizar la categoría');
              }
            );
          }
      }
  }

  cancel() {
      this.goBack();
  }
}

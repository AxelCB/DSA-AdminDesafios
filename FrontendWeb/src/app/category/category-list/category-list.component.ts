/**
 * Created by acollard on 31/12/16.
 */

import {Component, OnInit, ViewChild} from '@angular/core';
import { Category } from '../category';
import {CategoryService} from '../category.service';
import {DialogConfirmDeleteComponent} from '../../dialog-confirm-delete/dialog-confirm-delete.component';
import {MessagesService} from '../../alert-messages/alert-messages.service';
import {Message} from '../../alert-messages/message';

@Component({
  moduleId: module.id,
  selector: 'app-category-list',
  templateUrl: 'category-list.component.html',
  styleUrls: ['category-list.component.css'],
  providers: [CategoryService],
})

export class CategoryListComponent implements OnInit {
  categories: Category[];
  @ViewChild(DialogConfirmDeleteComponent)
  private deleteDialog: DialogConfirmDeleteComponent;

  constructor(private categoryService: CategoryService, private messagesService: MessagesService) {}

  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }

  delete(category: Category): void {
    this.deleteDialog.open(() => this.categoryService.delete(category).subscribe(
      () => {
        this.getCategories();
        this.messagesService.sendMessage(new Message('Categoría borrada correctamente', false));
      },
      () => {
        this.messagesService.appendToMessage('No se pudo borrar la categoría');
      })
    );
  }
}

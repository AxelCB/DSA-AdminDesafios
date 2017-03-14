import { MaterialModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
import { DialogConfirmDeleteComponent } from './dialog-confirm-delete/dialog-confirm-delete.component';
import { ConfigurationListComponent } from './configuration/configuration-list/configuration-list.component';
import { ConfigurationDetailComponent } from './configuration/configuration-detail/configuration-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoryListComponent,
    CategoryDetailComponent,
    DialogConfirmDeleteComponent,
    ConfigurationListComponent,
    ConfigurationDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: 'categories',
        component: CategoryListComponent
      },
      {
        path: 'categories/:id',
        component: CategoryDetailComponent
      },
      {
        path: 'categories/new',
        component: CategoryDetailComponent
      },
      {
        path: 'configurations',
        component: ConfigurationListComponent
      },
      {
        path: 'configurations/:id',
        component: ConfigurationDetailComponent
      }
    ]),
    MaterialModule
  ],
  entryComponents: [DialogConfirmDeleteComponent],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }

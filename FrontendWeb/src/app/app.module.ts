import { MaterialModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { CategoryListComponent } from './category/category-list/category-list.component'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoryListComponent,
    CategoryDetailComponent
  ],
  imports: [
    NgbModule.forRoot(),
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
      }
    ])
    ,MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }

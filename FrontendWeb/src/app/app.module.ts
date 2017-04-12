import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
/*
import { ChallengeListComponent } from './challenge/challenge-list/challenge-list.component';
import { ChallengeDetailComponent } from './challenge/challenge-detail/challenge-detail.component';
import { DialogConfirmDeleteComponent } from './dialog-confirm-delete/dialog-confirm-delete.component';
import { ConfigurationListComponent } from './configuration/configuration-list/configuration-list.component';
import { ConfigurationDetailComponent } from './configuration/configuration-detail/configuration-detail.component';
*/

@NgModule({
  declarations: [
    AppComponent,
    CategoryListComponent,
    CategoryDetailComponent,
    /*
    ChallengeListComponent,
    ChallengeDetailComponent,
    DialogConfirmDeleteComponent,
    ConfigurationListComponent,
    ConfigurationDetailComponent
    */
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
      /*
      /*
      {
        path: 'challenges',
        component: ChallengeListComponent
      },
      {
        path: 'challenges/:id',
        component: ChallengeDetailComponent
      },
      {
        path: 'challenges/new',
        component: ChallengeDetailComponent
      },
      {
        path: 'configurations',
        component: ConfigurationListComponent
      },
      {
        path: 'configurations/:id',
        component: ConfigurationDetailComponent
      }
      */
    ])
  ],
  // entryComponents: [DialogConfirmDeleteComponent],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }

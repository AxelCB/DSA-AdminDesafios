import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
import { ChallengeListComponent } from './challenge/challenge-list/challenge-list.component';
import { ChallengeDetailComponent } from './challenge/challenge-detail/challenge-detail.component';
import { DialogConfirmDeleteComponent } from './dialog-confirm-delete/dialog-confirm-delete.component';
import { ConfigurationListComponent } from './configuration/configuration-list/configuration-list.component';
import { ConfigurationDetailComponent } from './configuration/configuration-detail/configuration-detail.component';
import { AlertMessagesComponent } from './alert-messages/alert-messages.component';
import { MessagesService } from './alert-messages/alert-messages.service';
import { AuthLoginComponent } from './auth/auth-login/auth-login.component';
import { AuthService } from './auth/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    CategoryListComponent,
    CategoryDetailComponent,
    DialogConfirmDeleteComponent,
    ChallengeListComponent,
    ChallengeDetailComponent,
    ConfigurationListComponent,
    ConfigurationDetailComponent,
    AlertMessagesComponent,
    AuthLoginComponent
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
      },
      {
        path: 'login',
        component: AuthLoginComponent
      }
    ])
  ],
  providers: [MessagesService, AuthService],
  bootstrap: [AppComponent]
})

export class AppModule { }

import {Component, OnInit, ViewChild} from '@angular/core';
import {MessagesService} from './alert-messages/alert-messages.service';
import {AlertMessagesComponent} from './alert-messages/alert-messages.component';
import {AuthService} from './auth/auth.service';
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'Administración de Desafíos';
  loggedIn = false;

  @ViewChild(AlertMessagesComponent)
  private alertMessagesComponent: AlertMessagesComponent;

  constructor(private messagesService: MessagesService, private authService: AuthService, private router: Router) {
    messagesService.missionReceived$.subscribe(message => {
      this.alertMessagesComponent.updateMessage(message);
    });
    messagesService.missionReceivedToAppend$.subscribe(messageToAppend => {
      this.alertMessagesComponent.appendToMessage(messageToAppend);
    });
    this.authService.loggedInStatusChanged$.subscribe(loggedInStatus => {
      if (loggedInStatus) {
        this.loggedIn = true;
        this.router.navigate(['/']);
      } else {
        this.loggedIn = false;
        this.router.navigate(['/login']);
      }
    });
  }

  ngOnInit() {
    this.authService.validateSession().subscribe(status => this.loggedIn = status || false, () => {});
  }

  logout() {
    this.authService.logout().subscribe(() => this.authService.updateLoggedInStatus(false));
  }
}

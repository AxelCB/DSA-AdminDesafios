import {Component, OnInit, ViewChild} from '@angular/core';
import {MessagesService} from './alert-messages/alert-messages.service';
import {AlertMessagesComponent} from './alert-messages/alert-messages.component';
import {AuthService} from './auth/auth.service';
import {Router, NavigationStart} from '@angular/router';
import {MessagePriority} from './alert-messages/message';

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

    this.router.events.subscribe(event => {
      if (this.alertMessagesComponent.message != null && this.alertMessagesComponent.message.isError) {
        if (this.alertMessagesComponent.message.priority !== MessagePriority.HIGH) {
          this.alertMessagesComponent.clearMessage();
        };
      };
    });
  }

  ngOnInit() {
    this.authService.validateSession().subscribe(status => this.loggedIn = status || false, () => {});
  }

  logout() {
    this.authService.logout().subscribe(() => this.authService.updateLoggedInStatus(false));
  }
}

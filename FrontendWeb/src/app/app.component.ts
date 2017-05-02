import {Component, ViewChild} from '@angular/core';
import {MessagesService} from './alert-messages/alert-messages.service';
import {AlertMessagesComponent} from './alert-messages/alert-messages.component';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Administración de Desafíos';

  @ViewChild(AlertMessagesComponent)
  private alertMessagesComponent: AlertMessagesComponent;

  constructor(private messagesService: MessagesService) {
    messagesService.missionReceived$.subscribe(message => {
      this.alertMessagesComponent.updateMessage(message);
    });
    messagesService.missionReceivedToAppend$.subscribe(messageToAppend => {
      this.alertMessagesComponent.appendToMessage(messageToAppend);
    });
  }
}

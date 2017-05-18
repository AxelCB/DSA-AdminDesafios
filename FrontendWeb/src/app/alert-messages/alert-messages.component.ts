import { Component, OnInit } from '@angular/core';
import {Message, MessagePriority} from './message';

@Component({
  selector: 'app-alert-messages',
  templateUrl: './alert-messages.component.html',
  styleUrls: ['./alert-messages.component.css']
})
export class AlertMessagesComponent implements OnInit {
  message: Message;


  constructor() { }

  ngOnInit() {
  }

  updateMessage(message: Message) {
    if ((this.message != null && (this.message.priority <= message.priority || this.message.content == null)) || this.message == null) {
      this.message = message;
    }
  }

  appendToMessage(messageToAppend: string) {
    this.message.content += messageToAppend;
  }

  clearMessage() {
    this.message = null;
  }

}

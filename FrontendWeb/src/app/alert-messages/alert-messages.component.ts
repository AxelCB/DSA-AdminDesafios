import { Component, OnInit } from '@angular/core';
import {Message} from './message';

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

  updateMessage(message: Message){
    this.message = message;
  }

}

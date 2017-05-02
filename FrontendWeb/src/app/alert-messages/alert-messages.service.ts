/**
 * Created by acollard on 1/1/17.
 */

import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';
import {Message} from './message';

@Injectable()
export class MessagesService {

  // Observable string sources
  private messageReceivedSource = new Subject<Message>();
  private messageReceivedToAppendSource = new Subject<string>();

  // Observable string streams
  missionReceived$ = this.messageReceivedSource.asObservable();
  missionReceivedToAppend$ = this.messageReceivedToAppendSource.asObservable();

  // Service message commands
  sendMessage(message: Message) {
    this.messageReceivedSource.next(message);
  }

  appendToMessage(messageToAppend: string) {
    this.messageReceivedToAppendSource.next(messageToAppend);
  }

}

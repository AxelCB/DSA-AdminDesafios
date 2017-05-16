export class Message {
  content: string;
  isError: boolean;
  responseCode: number;
  priority: MessagePriority;

  constructor(content = '', isError = false, responseCode?: number, priority?: MessagePriority) {
    this.content = content;
    this.isError = isError;
    if (responseCode) {
      this.responseCode = responseCode;
    }
    if (priority) {
      this.priority = priority;
    } else {
      this.priority = MessagePriority.NORMAL;
    }
  }
}

export enum MessagePriority {
  NORMAL,
  HIGH
}

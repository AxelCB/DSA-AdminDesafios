export class Message {
  content: string;
  isError: boolean;
  responseCode: number;

  constructor(content = '', isError = false, responseCode?: number) {
    this.content = content;
    this.isError = isError;
    if (responseCode) {
      this.responseCode = responseCode;
    }
  }
}

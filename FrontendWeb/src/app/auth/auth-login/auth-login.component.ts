import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {MessagesService} from '../../alert-messages/alert-messages.service';
import {Message} from '../../alert-messages/message';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-auth-login-component',
  templateUrl: './auth-login.component.html',
  styleUrls: ['./auth-login.component.css']
})
export class AuthLoginComponent implements OnInit {
  user: User = new User();
  isProcessing = false;

  constructor(private messagesService: MessagesService, private authService: AuthService) { }

  ngOnInit() {
  }

  login(): void {
    let errorMessage = '';
    if (this.user.username == null || this.user.username === '') {
      errorMessage = 'Debe completar el nombre de usuario.';
    }
    if (this.user.password == null || this.user.password === '') {
      errorMessage += 'Debe completar la contraseña.';
    }
    if (errorMessage !== '') {
      this.messagesService.sendMessage(new Message(errorMessage, true));
    } else {
      this.isProcessing = true;
      this.authService.login(this.user).subscribe(loggedIn => {
        this.authService.updateLoggedInStatus(loggedIn);
        this.isProcessing = false;
      });
    }
  }

}

import {Component, OnInit, ViewChild} from '@angular/core';
import {Challenge} from '../challenge';
import {ChallengeService} from '../challenge.service';
import {DialogConfirmDeleteComponent} from '../../dialog-confirm-delete/dialog-confirm-delete.component';
import {Message} from '../../alert-messages/message';
import {MessagesService} from '../../alert-messages/alert-messages.service';

@Component({
  moduleId: module.id,
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrls: ['./challenge-list.component.css'],
  providers: [ChallengeService]
})
export class ChallengeListComponent implements OnInit {
  challenges: Challenge[];
  @ViewChild(DialogConfirmDeleteComponent)
  private deleteDialog: DialogConfirmDeleteComponent;

  constructor(private challengeService: ChallengeService, private messagesService: MessagesService) { }

  ngOnInit() {
    this.getChallenges();
  }

  getChallenges(): void {
    this.challengeService.getChallenges().subscribe(challenges => this.challenges = challenges);
  }

  delete(challenge: Challenge): void {
    this.deleteDialog.open(() => this.challengeService.delete(challenge).subscribe(
      () => {
        this.getChallenges();
        this.messagesService.sendMessage(new Message('Desafío borrado correctamente', false));
      },
      () => {
        this.messagesService.appendToMessage('No se pudo borrar el desafío');
      })
    );
  }

}

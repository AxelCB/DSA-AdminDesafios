import { Component, OnInit } from '@angular/core';
import {Challenge} from "../challenge";
import {ChallengeService} from "../challenge.service";

@Component({
  moduleId: module.id,
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrls: ['./challenge-list.component.css'],
  providers: [ChallengeService]
})
export class ChallengeListComponent implements OnInit {
  challenges: Challenge[];

  constructor(private challengeService: ChallengeService  ) { }

  ngOnInit() {
    this.getChallenges();
  }

  getChallenges():void {
    this.challengeService.getChallenges().subscribe(challenges => this.challenges = challenges);
  }

  delete(challenge: Challenge): void {
    /*
    let dialogRef = this.dialog.open(DialogConfirmDeleteComponent);
    dialogRef.afterClosed().subscribe(result => {
      if ( result ) {
        this.challengeService.delete(challenge).subscribe(()=> this.getChallenges());
      }
    });
    */
  }

}

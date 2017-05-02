import { Component, OnInit } from '@angular/core';
import {ChallengeService} from '../challenge.service';
import {CategoryService} from '../../category/category.service';
import {Challenge} from '../challenge';
import {Category} from '../../category/category';
import {ActivatedRoute, Params, Router} from '@angular/router';
import { Location } from '@angular/common';
import {isNullOrUndefined} from 'util';
import {Hint} from '../../hint/hint';
import {MessagesService} from '../../alert-messages/alert-messages.service';
import {Message} from '../../alert-messages/message';
import {NOT_FOUND} from 'http-status-codes';

@Component({
  moduleId: module.id,
  selector: 'app-challenge-detail',
  templateUrl: './challenge-detail.component.html',
  styleUrls: ['./challenge-detail.component.css'],
  providers: [ChallengeService, CategoryService]
})
export class ChallengeDetailComponent implements OnInit {
  challenge: Challenge = new Challenge();
  categories: Category[];
  otherChallenges: Challenge[];
  displayHint1 = false;
  displayHint2 = false;
  fileChanged = false;
  fileList: FileList;

  constructor(private challengeService: ChallengeService, private categoryService: CategoryService, private router: Router,
              private route: ActivatedRoute, private location: Location, private messagesService: MessagesService) { }

  ngOnInit(): void {
    this.getCategories();
    this.route.params
      .switchMap((params: Params) => this.challengeService.getChallenge(+params['id']))
      .subscribe(challenge => {
          this.challenge = challenge;
          this.displayHint1 = (challenge.hint1 != null && challenge.hint1.description != null);
          this.displayHint2 = (challenge.hint2 != null && challenge.hint2.description != null);
          this.getChallenges();
      },
        error => {
        if ((<Message>error).responseCode != null && (<Message>error).responseCode === NOT_FOUND) {
          this.router.navigate(['/challenges']);
        }
      });
  }

  goBack() {
    this.location.back();
  }

  getCategories(): void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }

  getChallenges(): void {
    this.challengeService.getChallenges().subscribe(
      challenges => this.otherChallenges = challenges.filter(
        challenge => challenge.id !== this.challenge.id)
    );
  }

  save() {
    if ( isNullOrUndefined(this.fileList) && !this.fileChanged ) {
      if ( this.validateChallenge(this.challenge) ) {
        if ( isNullOrUndefined( this.challenge.id ) ) {
          this.challengeService.create(this.challenge).subscribe(() => {
            this.goBack();
            this.messagesService.sendMessage(new Message('Desafío creado correctamente', false));
          },
          () => {
            this.messagesService.appendToMessage('No se pudo crear el desafío');
          }
        );
        } else {
          this.challengeService.update(this.challenge).subscribe(
          () => {
            this.goBack();
            this.messagesService.sendMessage(new Message('Desafío actualizado correctamente', false));
          },
          () => {
            this.messagesService.appendToMessage('No se pudo actualizar el desafío');
          }
        );
        }
      } else {
        this.messagesService.sendMessage(new Message('Debe completar todos los campos obligatorios', true));
      }
    } else {
      this.uploadFile();
    }
  }

  toggleHint1Display() {
    this.challenge.hint1 = new Hint();
  }

  toggleHint2Display() {
    this.challenge.hint2 = new Hint();
  }

  cancel() {
    this.goBack();
  }

  validateChallenge(challenge: Challenge): boolean {
    if ( isNullOrUndefined(challenge.title) || challenge.title === '') {
        return false;
    }
    if ( isNullOrUndefined(challenge.category) || isNullOrUndefined(challenge.category.id)) {
      return false;
    }
    if ( isNullOrUndefined(challenge.points) || challenge.points < 0) {
      return false;
    }
    if ( isNullOrUndefined(challenge.description) || challenge.title === '') {
      return false;
    }
    if ( isNullOrUndefined(challenge.validAnswer) || challenge.validAnswer === '') {
      return false;
    }
    if ( isNullOrUndefined(challenge.answerDescription) || challenge.answerDescription === '') {
      return false;
    }
    return true;
  }
  fileChange(event) {
    this.fileChanged = true;
    this.fileList = event.target.files;
  }

  deleteFile() {
    this.fileChanged = false;
    this.fileList = null;
    this.challenge.fileName = null;
    this.challenge.attachedFileUrl = null;
  }

  uploadFile() {
    if (this.fileList.length > 0) {
      let file: File = this.fileList[0];
      this.challengeService.uploadChallengeFile(file).subscribe(
        data => {
          if (data) {
            this.fileChanged = false;
            this.fileList = null;
            this.challenge.attachedFileUrl = data;
            this.challenge.fileName = file.name;
            this.save();
          }
        },
        error => console.log(error)
      );
    }
  }

  challengeEqualsById(challenge1: Challenge, challenge2: Challenge) {
      if (challenge1 != null && challenge2 != null && challenge1.id != null && challenge2.id != null) {
        return challenge1.id === challenge2.id;
      } else if (challenge1 == null && challenge2 == null) {
        return true;
      } else {
        return false;
      }
  }

  categoryEqualsById(category1: Category, category2: Category) {
    if (category1 != null && category2 != null && category1.id != null && category2.id != null) {
      return category1.id === category2.id;
    } else {
      return false;
    }
  }
}

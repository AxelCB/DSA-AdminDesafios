import { Component, OnInit } from '@angular/core';
import {ChallengeService} from "../challenge.service";
import {CategoryService} from "../../category/category.service";
import {Challenge} from "../challenge";
import {Category} from "../../category/category";
import {ActivatedRoute, Params} from "@angular/router";
import { Location } from '@angular/common';
import {isNullOrUndefined} from "util";

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

  constructor(private challengeService: ChallengeService, private categoryService: CategoryService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {
    this.getCategories();
    this.route.params
      .switchMap((params: Params) => this.challengeService.getChallenge(+params['id']))
      .subscribe(challenge => this.challenge = challenge);
  }

  goBack() {
    this.location.back();
  }

  getCategories():void {
    this.categoryService.getCategories().subscribe(categories => this.categories = categories);
  }

  save() {
    if ( this.validateChallenge(this.challenge) ) {
      if ( isNullOrUndefined( this.challenge.id ) ) {
        this.challengeService.create(this.challenge).subscribe(() => this.goBack());
      } else {
        this.challengeService.update(this.challenge).subscribe(() => this.goBack());
      }
    }
  }

  cancel() {
    this.goBack();
  }

  validateChallenge(challenge: Challenge): boolean {
    return true;
  }
}

import {Category} from "../category/category";
import {Hint} from "../hint/hint";

export class Challenge {
  id: number;
  title: string;
  category: Category;
  points: number;
  description: string;
  hint1: Hint;
  hint2: Hint;
  attachedFileUrl: string;
  validAnswer: string;
  answerDescription: string;
  nextChallenge: Challenge;

  constructor() {
    this.hint1 = new Hint();
    this.hint2 = new Hint();
  }
}

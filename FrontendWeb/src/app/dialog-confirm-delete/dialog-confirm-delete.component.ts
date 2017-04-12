import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dialog-confirm-delete',
  templateUrl: './dialog-confirm-delete.component.html',
  styleUrls: ['./dialog-confirm-delete.component.css']
})
export class DialogConfirmDeleteComponent implements OnInit {

  constructor(private callback: Function) { }

  ngOnInit() {
  }

  confirmDeletion() {
    this.callback();
  }

}

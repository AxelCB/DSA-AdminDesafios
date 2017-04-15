import {Component, Input, OnInit} from '@angular/core';
declare var $: JQueryStatic;

@Component({
  selector: 'app-dialog-confirm-delete',
  templateUrl: './dialog-confirm-delete.component.html',
  styleUrls: ['./dialog-confirm-delete.component.css']
})
export class DialogConfirmDeleteComponent implements OnInit {
  callback: Function;

  constructor() {}

  ngOnInit() {
  }

  open(deleteCallbackFunction: Function) {
    this.callback = deleteCallbackFunction;
    $('#deleteConfirmationModal').modal({show: true});
  }

  confirmDeletion() {
    this.callback();
    $('#deleteConfirmationModal').modal('hide');
  }

}

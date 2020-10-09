import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Component, Inject, OnInit} from '@angular/core';


@Component({
  selector: 'app-dialog-example',
  templateUrl: './add-to-boss.component.html',
  styleUrls: ['./add-to-boss.component.scss']
})
export class DialogAddToBossComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
  }

}

import { Component } from '@angular/core';
import {FormGroup, FormControl, FormBuilder} from "@angular/forms";

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Expense Tracker';
  selected: string;
  appComponentForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) {
    this.appComponentForm = formBuilder.group({
      "filterQuery": new FormControl()
    });
  }

}

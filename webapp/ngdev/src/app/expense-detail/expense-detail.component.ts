/**
 * Created by AT003053 on 27/04/2017.
 */

import { Component, Input } from "@angular/core";
import { Expense }          from "../expense";

@Component({
  selector: 'expense-detail',
  templateUrl: './expense-detail.component.html',
})

export class ExpenseDetailComponent {
  @Input() expense: Expense;
}

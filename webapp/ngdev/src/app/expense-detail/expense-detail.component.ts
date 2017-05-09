/**
 * Created by AT003053 on 27/04/2017.
 */

import {Component, Input, OnInit, NgModule} from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';
import { Expense }                  from '../expense';
import { ExpenseService }           from '../expense.service';
import 'rxjs/add/operator/switchMap';
import {ExpenseTypes} from "../expense-types.enum";
import {FormBuilder, FormGroup, FormControl} from "@angular/forms";

@Component({
  selector: 'expense-detail',
  templateUrl: './expense-detail.component.html',
  styleUrls: ['./expense-detail.component.scss']
})

export class ExpenseDetailComponent implements OnInit {
  expense: Expense;
  types = ExpenseTypes;
  type: string;
  expenseDetailForm: FormGroup;

  constructor(
    private expenseService: ExpenseService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) {
    this.expenseDetailForm = formBuilder.group({
      "expense.name": new FormControl(),
      "expense.description": new FormControl(),
      "expense.date": new FormControl(),
      "expense.type": new FormControl(),
      "expense.amount": new FormControl()
    });
  }

  ngOnInit(): void {
    this.route.params.switchMap((params: Params) => this.expenseService.getExpense(+params['id']))
      .subscribe(expense => this.expense = expense);
  }

  goBack(): void {
    this.location.back();
  }
}

/**
 * Created by AT003053 on 27/04/2017.
 */

import { Component, Input, OnInit } from '@angular/core';
import { Expense }                  from '../expense';
import { ExpenseService }           from '../expense.service';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'expense-detail',
  templateUrl: './expense-detail.component.html',
})

export class ExpenseDetailComponent implements OnInit {
  expense: Expense;

  constructor(
    private expenseService: ExpenseService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params.switchMap((params: Params) => this.expenseService.getExpense(+params['id']))
      .subscribe(expense => this.expense = expense);
  }

  goBack(): void {
    this.location.back();
  }
}

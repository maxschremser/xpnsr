/**
 * Created by AT003053 on 27/04/2017.
 */

import { Component, Input, OnInit } from '@angular/core';
import { Expense }                  from '../expense';
import { ExpenseService }           from '../expense.service';
import { Types }                    from "../types.enum";
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'expense-detail',
  templateUrl: './expense-detail.component.html',
  styleUrls: ['./expense-detail.component.scss']
})

export class ExpenseDetailComponent implements OnInit {
  expense: Expense;
  types: Types;

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

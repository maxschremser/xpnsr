import { Component, OnInit } from '@angular/core';
import { ExpenseService }    from '../expense.service';
import { Expense }           from "../expense";

@Component({
  selector: 'expense-list',
  templateUrl: './expense-list.component.html',
  providers:      [ExpenseService]
})
export class ExpenseListComponent implements OnInit {
  expenses: Expense[];
  selectedExpense: Expense;
  errorMessage: string;
  mode = 'Observable';

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.getExpenses();
  }

  getExpenses(): void {
    this.expenseService.getExpenses().subscribe(
      expenses => this.expenses = expenses,
      error => this.errorMessage = <any>error
    );
  }

  onSelect(expense: Expense): void {
    this.selectedExpense = expense;
  }
}

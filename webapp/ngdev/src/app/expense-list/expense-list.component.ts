import { Component, OnInit } from '@angular/core';
import { ExpenseService }    from '../expense.service';
import { Expense }           from '../expense';
import { Router }            from '@angular/router';

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

  constructor(
    private router: Router,
    private expenseService: ExpenseService) {}

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
    this.routeExpenseDetail();
  }

  routeExpenseDetail(): void {
    this.router.navigate(['/detail', this.selectedExpense.id]);
  }
}

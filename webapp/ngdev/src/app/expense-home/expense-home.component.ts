import { Component, OnInit } from '@angular/core';
import { ExpenseService }    from '../expense.service';
import { Expense }           from '../expense';
import { Router }            from '@angular/router';

@Component({
  selector: 'app-expense-home',
  templateUrl: './expense-home.component.html',
  styleUrls: ['./expense-home.component.scss']
})

export class ExpenseHomeComponent implements OnInit {

  topExpenses: Expense[] = [];
  selectedExpense: Expense;
  errorMessage: string;

  constructor(
    private router: Router,
    private expenseService: ExpenseService) { }

  ngOnInit() {
    this.getTopExpenses();
  }

  getTopExpenses(): void {
    this.expenseService.getExpenses().subscribe(
      expenses => this.topExpenses = expenses.slice(1,5),
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

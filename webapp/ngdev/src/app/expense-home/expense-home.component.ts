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

  todaysExpenses: Expense[] = [];
  sum: number = 0;
  today: Date;
  selectedExpense: Expense;
  errorMessage: string;

  constructor(
    private router: Router,
    private expenseService: ExpenseService) { }

  ngOnInit() {
    this.today = new Date();
    this.getTodaysExpenses();
  }

  getTodaysExpenses(): void {
    this.expenseService.getTodaysExpenses().subscribe(
      expenses => {
          this.todaysExpenses = expenses;
          for (var i = 0; i < this.todaysExpenses.length; i++) {
            this.sum += this.todaysExpenses[i].amount;
          }
        },
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

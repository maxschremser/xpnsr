import { Component, OnInit } from '@angular/core';
import { ExpenseService }    from '../expense.service';
import { Expense }           from '../expense';
import { Router }            from '@angular/router';

@Component({
  selector: 'expense-list',
  templateUrl: './expense-list.component.html',
  styleUrls: ['./expense-list.component.scss'],
  providers: [ExpenseService]
})
export class ExpenseListComponent implements OnInit {
  expenses: Expense[];
  selectedExpense: Expense;
  today: Date;
  sum: number = 0;
  errorMessage: string;
  mode = 'Observable';

  constructor(
    private router: Router,
    private expenseService: ExpenseService) {}

  ngOnInit(): void {
    this.today = new Date();
    this.getExpenses();
  }

  getExpenses(): void {
    this.expenseService.getExpenses().subscribe(
      expenses => {
          this.expenses = expenses;
          for (var i = 0; i < this.expenses.length; i++) {
            this.sum += this.expenses[i].amount;
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

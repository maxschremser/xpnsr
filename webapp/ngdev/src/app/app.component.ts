import { Component,
  NgModule,
  OnInit
}                                     from '@angular/core';
import { ExpenseService }             from './expense.service';
import { Expense }                    from "./expense";

@Component({
  selector:       'app-root',
  templateUrl:    './app.component.html',
  styleUrls:      ['./app.component.scss'],
  providers: [ExpenseService]
})
export class AppComponent implements OnInit {
  title: 'Expense Tracker';
  expenses: Expense[];
  selectedExpense: Expense;
  errorMessage: string;
  mode = 'Observable';

  constructor(private expenseService: ExpenseService) {}

  ngOnInit(): void {
    // this.getExpenses();
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

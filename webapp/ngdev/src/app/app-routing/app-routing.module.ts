import { NgModule }               from '@angular/core';
import { CommonModule }           from '@angular/common';
import { ExpenseDetailComponent } from '../expense-detail/expense-detail.component';
import { ExpenseListComponent }   from '../expense-list/expense-list.component';
import { ExpenseHomeComponent }   from '../expense-home/expense-home.component';
import { Routes, RouterModule }   from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home',       component: ExpenseHomeComponent},
  { path: 'list',       component: ExpenseListComponent},
  { path: 'detail/:id', component: ExpenseDetailComponent}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }

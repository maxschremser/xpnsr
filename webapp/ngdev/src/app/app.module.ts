import { BrowserModule }              from '@angular/platform-browser';
import { NgModule }                   from '@angular/core';
import { FormsModule }                from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';
import { NgbModule }                  from '@ng-bootstrap/ng-bootstrap';
import { Angular2FontAwesomeModule }  from 'angular2-font-awesome/angular2-font-awesome';
import { AppComponent }               from './app.component';
import { ExpenseService }             from './expense.service';
import { AppRoutingModule }           from './app-routing/app-routing.module';
import { ExpenseHomeComponent }       from './expense-home/expense-home.component';
import { ExpenseListComponent }       from './expense-list/expense-list.component';
import { ExpenseDetailComponent }     from './expense-detail/expense-detail.component';
import { ExpenseComponent }           from './expense/expense.component';

@NgModule({
  declarations: [
    AppComponent,
    ExpenseComponent,
    ExpenseHomeComponent,
    ExpenseListComponent,
    ExpenseDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    Angular2FontAwesomeModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  providers: [ExpenseService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

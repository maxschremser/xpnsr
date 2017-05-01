import { BrowserModule }                  from '@angular/platform-browser';
import { NgModule }                       from '@angular/core';
import { FormsModule }                    from '@angular/forms';
import { HttpModule, JsonpModule }        from '@angular/http';
import { NgbModule }                      from '@ng-bootstrap/ng-bootstrap';
import { Angular2FontAwesomeModule }      from 'angular2-font-awesome/angular2-font-awesome';
import { DataTableModule}                 from 'angular2-datatable';
import { MdCardModule }                   from '@angular2-material/card';
import { MdButtonModule }                 from '@angular2-material/button';
import { MdIconModule, MdIconRegistry }   from '@angular2-material/icon';
import { MdToolbarModule }                from '@angular2-material/toolbar';
import { MdInputModule }                  from '@angular2-material/input';
import { AppComponent }                   from './app.component';
import { ExpenseService }                 from './expense.service';
import { DataFilterPipe }                 from './data-filter.pipe';
import { AppRoutingModule }               from './app-routing/app-routing.module';
import { ExpenseComponent }               from './expense/expense.component';
import { ExpenseHomeComponent }           from './expense-home/expense-home.component';
import { ExpenseListComponent }           from './expense-list/expense-list.component';
import { ExpenseDetailComponent }         from './expense-detail/expense-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    ExpenseComponent,
    ExpenseHomeComponent,
    ExpenseListComponent,
    ExpenseDetailComponent,
    DataFilterPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    Angular2FontAwesomeModule,
    MdIconModule,
    MdButtonModule,
    MdCardModule,
    MdToolbarModule,
    MdInputModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    DataTableModule
  ],
  providers: [ExpenseService, MdIconRegistry],
  bootstrap: [AppComponent]
})
export class AppModule {
}

import { BrowserModule }                  from '@angular/platform-browser';
import { NgModule }                       from '@angular/core';
import {
  ReactiveFormsModule,
  FormGroup,
  FormControl,
  Validators,
  FormBuilder
}                                         from '@angular/forms';
import { HttpModule, JsonpModule }        from '@angular/http';
import { MdCardModule }                   from '@angular/material';
import { MdButtonModule }                 from '@angular/material';
import { MdIconModule, MdIconRegistry }   from '@angular/material';
import { MdToolbarModule }                from '@angular/material';
import { MdInputModule }                  from '@angular/material';
import { MdSelectModule }                 from '@angular/material';
import { BrowserAnimationsModule }        from '@angular/platform-browser/animations';
import { platformBrowserDynamic }         from '@angular/platform-browser-dynamic';
import { NgbModule }                      from '@ng-bootstrap/ng-bootstrap';
import { Angular2FontAwesomeModule }      from 'angular2-font-awesome/angular2-font-awesome';
import { DataTableModule}                 from 'angular2-datatable';
import { AppComponent }                   from './app.component';
import { ExpenseService }                 from './expense.service';
import { DataFilterPipe }                 from './data-filter.pipe';
import { AppRoutingModule }               from './app-routing/app-routing.module';
import { ExpenseComponent }               from './expense/expense.component';
import { ExpenseHomeComponent }           from './expense-home/expense-home.component';
import { ExpenseListComponent }           from './expense-list/expense-list.component';
import { ExpenseDetailComponent }         from './expense-detail/expense-detail.component';
import { EnumSelectPipe }                 from './enum-select.pipe';
import 'hammerjs';
import 'rxjs/Rx';

@NgModule({
  declarations: [
    AppComponent,
    ExpenseComponent,
    ExpenseHomeComponent,
    ExpenseListComponent,
    ExpenseDetailComponent,
    DataFilterPipe,
    EnumSelectPipe
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpModule,
    JsonpModule,
    Angular2FontAwesomeModule,
    BrowserAnimationsModule,
    MdIconModule,
    MdButtonModule,
    MdCardModule,
    MdToolbarModule,
    MdInputModule,
    MdSelectModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    DataTableModule
  ],
  providers: [ExpenseService, MdIconRegistry],
  bootstrap: [AppComponent]
})

export class AppModule {
}

platformBrowserDynamic().bootstrapModule(AppModule);


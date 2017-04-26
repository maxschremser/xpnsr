import { BrowserModule }              from '@angular/platform-browser';
import { NgModule }                   from '@angular/core';
import { FormsModule }                from '@angular/forms';
import { HttpModule, JsonpModule }    from '@angular/http';
import { NgbModule }                  from '@ng-bootstrap/ng-bootstrap';
import { Angular2FontAwesomeModule }  from 'angular2-font-awesome/angular2-font-awesome';
import { NgbdTabsetBasic }            from './tabset-basic';
import { XtHomeList }                 from './xt-home-list'

import { AppComponent }               from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    NgbdTabsetBasic,
    XtHomeList
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    Angular2FontAwesomeModule,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

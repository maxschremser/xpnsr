import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgbModule }            from '@ng-bootstrap/ng-bootstrap';
import { NgbdTabsetBasic } from './tabset-basic';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    NgbdTabsetBasic
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

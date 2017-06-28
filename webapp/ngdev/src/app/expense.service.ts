import { Injectable }     from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { Expense }        from './expense';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class ExpenseService {
  private expenseUrl = '/api/expenses'; // URL to web API
  // private expenseUrl = 'http://localhost:9080/xpnsr/api/expenses'; // URL to web API

  constructor(private http: Http) { }

  getExpenses(): Observable<Expense[]> {
    return this.http.get(this.expenseUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getTodaysExpenses(): Observable<Expense[]> {
    return this.http.get(`${this.expenseUrl}/byDate/today`)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getExpense(id: number): Observable<Expense> {
    // api/expenses/{id}
    return this.http.get(`${this.expenseUrl}/${id}`)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    // return body.data || {};
    return body || {};
  }

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Keyboard } from './keyboard';
import { MessageService } from './message.service';
import { KEYBOARDS } from './mock-keyboards';


@Injectable({ providedIn: 'root' })
export class KeyboardService {

  private keyboardsUrl = 'api/keyboards';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET keyboards from the server */
  getKeyboards(): Observable<Keyboard[]> {
    const heroes = of(KEYBOARDS);
    return heroes;
  }

  /** GET keyboard by id. Return `undefined` when id not found */
  getKeyboardNo404<Data>(id: number): Observable<Keyboard> {
    const url = `${this.keyboardsUrl}/?id=${id}`;
    return this.http.get<Keyboard[]>(url)
      .pipe(
        map(keyboards => keyboards[0]), // returns a {0|1} element array
        tap(k => {
          const outcome = k ? 'fetched' : 'did not find';
          this.log(`${outcome} keyboard id=${id}`);
        }),
        catchError(this.handleError<Keyboard>(`getKeyboard id=${id}`))
      );
  }

  /** GET keyboard by id. Will 404 if id not found */
  getKeyboard(id: number): Observable<Keyboard> {
    const url = `${this.keyboardsUrl}/${id}`;
    return this.http.get<Keyboard>(url).pipe(
      tap(_ => this.log(`fetched keyboard id=${id}`)),
      catchError(this.handleError<Keyboard>(`getKeyboard id=${id}`))
    );
  }

  /* GET keyboards whose name contains search term */
  searchKeyboards(term: string): Observable<Keyboard[]> {
    if (!term.trim()) {
      // if not search term, return empty keyboard array.
      return of([]);
    }
    return this.http.get<Keyboard[]>(`${this.keyboardsUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found keyboards matching "${term}"`) :
         this.log(`no keyboards matching "${term}"`)),
      catchError(this.handleError<Keyboard[]>('searchKeyboards', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new keyboard to the server */
  addKeyboard(keyboard: Keyboard): Observable<Keyboard> {
    return this.http.post<Keyboard>(this.keyboardsUrl, keyboard, this.httpOptions).pipe(
      tap((newKeyboard: Keyboard) => this.log(`added keyboard w/ id=${newKeyboard.id}`)),
      catchError(this.handleError<Keyboard>('addKeyboard'))
    );
  }

  /** DELETE: delete the keyboard from the server */
  deleteKeyboard(id: number): Observable<Keyboard> {
    const url = `${this.keyboardsUrl}/${id}`;

    return this.http.delete<Keyboard>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted keyboard id=${id}`)),
      catchError(this.handleError<Keyboard>('deleteKeyboard'))
    );
  }

  /** PUT: update the keyboard on the server */
  updateKeyboard(keyboard: Keyboard): Observable<any> {
    return this.http.put(this.keyboardsUrl, keyboard, this.httpOptions).pipe(
      tap(_ => this.log(`updated keyboard id=${keyboard.id}`)),
      catchError(this.handleError<any>('updateKeyboard'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a KeyboardService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`KeyboardService: ${message}`);
  }
}
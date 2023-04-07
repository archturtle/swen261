import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, map, Observable, tap } from 'rxjs';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _user: BehaviorSubject<User> = new BehaviorSubject<User>(<User>{});
  public readonly user$: Observable<User> = this._user.asObservable();

  private static HTTP_OPTIONS: object = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private router: Router, private httpService: HttpClient) { }

  getUser$(name: string): Observable<User> {
    const url = `http://localhost:8080/users/?name=${name}`;

    return this.httpService.get<User[]>(url)
      .pipe(
        map((result: User[]) => {
          return (result.length == 0) ? <User>{} : result[0]
        }),
        tap({
          next: (value: User) => {
            this._user.next(value);
          }
        })
      );
  }

  getUserById$(id: number): Observable<User> {
    const url = `http://localhost:8080/users/${id}`;

    return this.httpService.get<User>(url)
    .pipe(
      tap({
        next: (value: User) => {
          this._user.next(value);
        }
      })
    ); 
  }

  createUser$(user: User): Observable<User> {
    const url = `http://localhost:8080/users`;

    return this.httpService.post<User>(url, user, UserService.HTTP_OPTIONS)
      .pipe(
        tap({
          next: (value: User) => { 
            this._user.next(value) 
          }
        })
      );
  }

  addToCart$(userId: number, keyboardId: number, quantity: number): Observable<User> {
    return this.httpService.post<User>(`http://localhost:8080/users/${userId}/cart/?productId=${keyboardId}&quantity=${quantity}`, <User>{})
      .pipe(
        tap({
          next: (value: User) => {
            this._user.next(value);
          }
        })
      )
  } 

  removeFromCart$(userId: number, keyboardId: number, quantity: number): Observable<User> {
    return this.httpService.delete<User>(`http://localhost:8080/users/${userId}/cart/?productId=${keyboardId}&quantity=${quantity}`)
      .pipe(
        tap({
          next: (value: User) => {
            this._user.next(value);
          }
        })
      )
  } 

  logOut$(): void {
    localStorage.removeItem("user");

    this._user.next(<User>{});
    this.router.navigate(['/']);
  }
}

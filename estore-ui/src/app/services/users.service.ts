import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private _user: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);
  public readonly user$: Observable<User | null> = this._user.asObservable();

  private static HTTP_OPTIONS: object = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private router: Router, private httpService: HttpClient) { }

  getUser$(name: string): Observable<User | null> {
    const url = `http://localhost:8080/users/?name=${name}`;

    return this.httpService.get(url)
      .pipe(
        map((result: any) => {
          return result.map((r: any) => {
            return {
              id: r["id"],
              name: r["name"],
              role: r["role"],
              cart: r["cart"].map((v: any) => {
                return {
                  id: v["id"],
                  name: v["name"],
                  price: v["price"],
                  quantity: v["quantity"]
                }
              })
            }
          });
        }),
        map((result: User[]) => {
          return (result.length == 0) ? null : result[0]
        }),
        tap({
          next: (value: User | null) => {
            this._user.next(value);
          }
        })
      );
  }

  getUserById$(id: number): Observable<User> {
    const url = `http://localhost:8080/users/${id}`;

    return this.httpService.get(url)
    .pipe(
      map((r: any) => {
        return {
          id: r["id"],
          name: r["name"],
          role: r["role"],
          cart: r["cart"].map((v: any) => {
            return {
              id: v["id"],
              name: v["name"],
              price: v["price"],
              quantity: v["quantity"]
            }
          })
        }
      }),
      tap({
        next: (value: User) => {
          this._user.next(value);
        }
      })
    ); 
  }

  createUser$(user: User): Observable<User> {
    const url = `http://localhost:8080/users`;

    return this.httpService.post<User>(url, user, UsersService.HTTP_OPTIONS)
      .pipe(
        tap({
          next: (value: User) => { 
            this._user.next(value) 
          }
        })
      );
  }

  logOut$(): void {
    localStorage.removeItem("user");
    this._user.next(null);
    this.router.navigate(['/']);
  }
}

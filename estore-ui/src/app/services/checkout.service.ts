import { Injectable } from '@angular/core';
import { CheckoutData } from '../interfaces/checkout-data';
import { Observable, tap } from 'rxjs';
import { User } from '../interfaces/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private static HTTP_OPTIONS: object = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpService: HttpClient, private usersService: UsersService) { }

  checkout$(checkoutData: CheckoutData): Observable<User> {
    return this.httpService.post<User>('http://localhost:8080/checkout', checkoutData, CheckoutService.HTTP_OPTIONS)
  } 
}

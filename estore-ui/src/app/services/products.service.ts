import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private _products: BehaviorSubject<Keyboard[]> = new BehaviorSubject<Keyboard[]>([]);
  public readonly products$: Observable<Keyboard[]> = this._products.asObservable();

  constructor(private httpService: HttpClient) { }

  getProducts$(name: string | null): Observable<Keyboard[]> {
    const url = (name != null) ? "http://localhost:8080/keyboards" : `http://localhost:8080/keyboards/?name=${name}`;

    return this.httpService.get(url)
      .pipe(
        map((result: any) => {
          return result.map((r: any) => {
            return {
              id: r["id"],
              name: r["name"],
              price: r["price"],
              quantity: r["quantity"]
            }
          });
        }),
        tap({
          next: (value: Keyboard[]) => {
            this._products.next(value);
          }
        })
      )
  }
}

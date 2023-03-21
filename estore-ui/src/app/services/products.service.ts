import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private _products: BehaviorSubject<Keyboard[]> = new BehaviorSubject<Keyboard[]>([]);
  public readonly products$: Observable<Keyboard[]> = this._products.asObservable();
  private static HTTP_OPTIONS: object = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpService: HttpClient) { }

  getProducts$(name: string | null): Observable<Keyboard[]> {
    const url = (name == null) ? "http://localhost:8080/keyboards" : `http://localhost:8080/keyboards/?name=${name}`;

    return this.httpService.get<Keyboard[]>(url)
      .pipe(
        tap({
          next: (value: Keyboard[]) => {
            this._products.next(value);
          }
        })
      )
  }

  getProductById$(id: number): Observable<Keyboard> {
    return this.httpService.get<Keyboard>(`http://localhost:8080/keyboards/${id}`);
  }

  addProduct$(product: Keyboard): Observable<Keyboard> {
    return this.httpService.post<Keyboard>('http://localhost:8080/keyboards', product, ProductsService.HTTP_OPTIONS)
      .pipe(
        tap({
          next: (value: Keyboard) => {
            this._products.next([...this._products.value, value]);
          },
        })
      )
  }

  updateProduct$(product: Keyboard): Observable<Keyboard> {
    return this.httpService.put<Keyboard>('http://localhost:8080/keyboards/', product, ProductsService.HTTP_OPTIONS)
      .pipe(
        tap({
          next: (value: Keyboard) => {
            const val = this._products.value.map((curr: Keyboard) => curr.id == value.id ? value : curr);
            this._products.next(val);
          }
        })
      )
  }

  deleteProduct$(id: number): Observable<void> {
    const url = `http://localhost:8080/keyboards/${id}`;

    this._products.next(this._products.value.filter(item => item.id != id));
    return this.httpService.delete<void>(url);
  }
}

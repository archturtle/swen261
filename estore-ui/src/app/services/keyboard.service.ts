import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class KeyboardService {
  private _keyboards: BehaviorSubject<Keyboard[]> = new BehaviorSubject<Keyboard[]>([]);
  public readonly keyboards$: Observable<Keyboard[]> = this._keyboards.asObservable();
  private static HTTP_OPTIONS: object = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpService: HttpClient) { }

  getKeyboards$(name?: string): Observable<Keyboard[]> {
    const url = !name ? "http://localhost:8080/keyboards" : `http://localhost:8080/keyboards/?name=${name}`;

    return this.httpService.get<Keyboard[]>(url)
      .pipe(
        tap({
          next: (value: Keyboard[]) => {
            this._keyboards.next(value);
          }
        })
      )
  }

  getKeyboardById$(id: number): Observable<Keyboard> {
    return this.httpService.get<Keyboard>(`http://localhost:8080/keyboards/${id}`);
  }

  addKeyboard$(keyboard: Keyboard): Observable<Keyboard> {
    return this.httpService.post<Keyboard>('http://localhost:8080/keyboards', keyboard, KeyboardService.HTTP_OPTIONS)
      .pipe(
        tap({
          next: (value: Keyboard) => {
            this._keyboards.next([...this._keyboards.value, value]);
          },
        })
      )
  }

  updateKeyboard$(keyboard: Keyboard): Observable<Keyboard> {
    return this.httpService.put<Keyboard>('http://localhost:8080/keyboards/', keyboard, KeyboardService.HTTP_OPTIONS)
      .pipe(
        tap({
          next: (value: Keyboard) => {
            const val = this._keyboards.value.map((curr: Keyboard) => curr.id == value.id ? value : curr);
            this._keyboards.next(val);
          }
        })
      )
  }

  deleteKeyboard$(id: number): Observable<void> {
    const url = `http://localhost:8080/keyboards/${id}`;

    this._keyboards.next(this._keyboards.value.filter(item => item.id != id));
    return this.httpService.delete<void>(url);
  }
}

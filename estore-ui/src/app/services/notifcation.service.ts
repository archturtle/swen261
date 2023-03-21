import { EventEmitter, Injectable, Output } from '@angular/core';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class NotifcationService {
  @Output() searchStringChanged: EventEmitter<string> = new EventEmitter<string>();
  @Output() productSelected: EventEmitter<Keyboard> = new EventEmitter<Keyboard>()

  constructor() { }

  changeSearch(search: string): void {
    this.searchStringChanged.emit(search);
  }

  changeProduct(product: Keyboard): void {
    this.productSelected.emit(product);
  }
}

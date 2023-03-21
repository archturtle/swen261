import { EventEmitter, Injectable, Output } from '@angular/core';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class NotifcationService {
  @Output() searchStringChanged: EventEmitter<string> = new EventEmitter<string>();
  @Output() productSelected: EventEmitter<Keyboard | null> = new EventEmitter<Keyboard | null>()

  constructor() { }

  changeSearch(search: string): void {
    this.searchStringChanged.emit(search);
  }

  changeProduct(product: Keyboard | null): void {
    this.productSelected.emit(product);
  }
}

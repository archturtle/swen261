import { EventEmitter, Injectable, Output } from '@angular/core';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class NotifcationService {
  @Output() searchStringChanged: EventEmitter<string> = new EventEmitter<string>();
  @Output() keyboardSelected: EventEmitter<Keyboard> = new EventEmitter<Keyboard>()
  @Output() errorOccurred: EventEmitter<string> = new EventEmitter<string>();

  changeSearch(search: string): void {
    this.searchStringChanged.emit(search);
  }

  changeKeyboard(keyboard: Keyboard): void {
    this.keyboardSelected.emit(keyboard);
  }

  emitError(message: string): void {
    this.errorOccurred.emit(message);
  }
}
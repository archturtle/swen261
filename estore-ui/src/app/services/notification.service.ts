import { EventEmitter, Injectable, Output } from '@angular/core';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  @Output() searchStringChanged: EventEmitter<string> = new EventEmitter<string>();
  @Output() keyboardSelected: EventEmitter<Keyboard> = new EventEmitter<Keyboard>()
  @Output() messagePosted: EventEmitter<string> = new EventEmitter<string>();

  changeSearch(search: string): void {
    this.searchStringChanged.emit(search);
  }

  changeKeyboard(keyboard: Keyboard): void {
    this.keyboardSelected.emit(keyboard);
  }

  postMessage(message: string): void {
    this.messagePosted.emit(message);
  }
}

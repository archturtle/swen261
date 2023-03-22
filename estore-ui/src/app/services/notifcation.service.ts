import { EventEmitter, Injectable, Output } from '@angular/core';
import { Keyboard } from '../interfaces/keyboard';

@Injectable({
  providedIn: 'root'
})
export class NotifcationService {
  @Output() searchStringChanged: EventEmitter<string> = new EventEmitter<string>();
  @Output() keyboardSelected: EventEmitter<Keyboard | null> = new EventEmitter<Keyboard | null>()

  constructor() { }

  changeSearch(search: string): void {
    this.searchStringChanged.emit(search);
  }

  changeKeyboard(keyboard: Keyboard | null): void {
    this.keyboardSelected.emit(keyboard);
  }
}

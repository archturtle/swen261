import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotifcationService {
  @Output() searchStringChanged: EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  changeSearch(search: string): void {
    this.searchStringChanged.emit(search);
  }
}

import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent { 
  @Output() onSearchType: EventEmitter<string> = new EventEmitter();

  searchBarChanged(value: string): void {
    this.onSearchType.emit(value);
  }
}

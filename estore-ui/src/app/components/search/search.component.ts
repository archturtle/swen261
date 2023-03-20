import { Component, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchBox: FormControl = new FormControl('');
  @Output() onSearchType: EventEmitter<string> = new EventEmitter();

  constructor() { }

  onType(): void {
    this.onSearchType.emit(this.searchBox.value);
  }
}

import { Component, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NotifcationService } from 'src/app/services/notifcation.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchBox: FormControl = new FormControl('');
  @Output() onSearchType: EventEmitter<string> = new EventEmitter();

  constructor(private notifcationService: NotifcationService) { }

  onType(): void {
    this.onSearchType.emit(this.searchBox.value);
    this.notifcationService.changeSearch(this.searchBox.value);
  }
}

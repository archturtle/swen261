import { Component, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchBox: FormControl = new FormControl('');
  @Output() onSearchType: EventEmitter<string> = new EventEmitter();

  constructor(private NotificationService: NotificationService) { }

  onType(): void {
    this.onSearchType.emit(this.searchBox.value);
    this.NotificationService.changeSearch(this.searchBox.value);
  }
}

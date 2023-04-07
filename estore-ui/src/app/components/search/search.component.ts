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
  constructor(private notificationService: NotificationService) { }

  onType(): void {
    this.notificationService.searchChanged(this.searchBox.value);
  }
}

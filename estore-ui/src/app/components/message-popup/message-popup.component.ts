import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-message-popup',
  templateUrl: './message-popup.component.html',
  styleUrls: ['./message-popup.component.css']
})
export class MessagePopupComponent implements OnInit {
  showMessage: boolean = false;
  message: string = "";
  
  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.notificationService.messagePosted.subscribe((message: string) => {
      this.showMessage = true;
      this.message = message;

      setTimeout(() => {
        this.showMessage = false;
      }, 2500);
    });
  }
}

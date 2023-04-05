import { Component, OnInit } from '@angular/core';
import { NotifcationService } from 'src/app/services/notifcation.service';

@Component({
  selector: 'app-error-popup',
  templateUrl: './error-popup.component.html',
  styleUrls: ['./error-popup.component.css']
})
export class ErrorPopupComponent implements OnInit {
  showError: boolean = false;
  errorMessage: string = "";
  
  constructor(private notificationService: NotifcationService) { }

  ngOnInit(): void {
    this.notificationService.errorOccurred.subscribe((message: string) => {
      this.showError = true;
      this.errorMessage = message;

      setTimeout(() => {
        this.showError = false;
      }, 2500);
    });
  }
}

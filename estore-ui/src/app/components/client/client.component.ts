import { Component } from '@angular/core';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {
  userBox: string = "";

  userSearching(value: string): void {
    this.userBox = value;
  }
}

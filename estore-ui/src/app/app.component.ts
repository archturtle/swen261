import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'estore-ui';

  constructor(private UserService: UserService) { }

  ngOnInit(): void {
    const id: string | null = localStorage.getItem("user")
    if (id != null) {
      this.UserService.getUserById$(parseInt(id))
        .subscribe();
    }
  }
}

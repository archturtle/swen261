import { Component, OnInit } from '@angular/core';
import { UsersService } from './services/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'estore-ui';

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    const id: string | null = localStorage.getItem("user")
    if (id != null) {
      this.usersService.getUserById$(parseInt(id))
        .subscribe();
    }
  }
}

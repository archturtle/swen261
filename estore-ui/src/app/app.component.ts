import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  currentURL: string = this.router.url;

  constructor(private UserService: UserService, public router: Router) { }

  ngOnInit(): void {
    const id: string | null = localStorage.getItem("user")
    if (id != null) {
      this.UserService.getUserById$(parseInt(id))
        .subscribe();
    }
  }
}

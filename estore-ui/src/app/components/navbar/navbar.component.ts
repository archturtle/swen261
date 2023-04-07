import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent { 
  loggedInUser$: Observable<User> = this.userService.user$;
  currentURL: string = this.router.url;

  constructor(private userService: UserService, private router: Router) { }

  processLogout(): void {
    this.userService.logOut$();
  }
}

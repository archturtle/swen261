import { Component, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent { 
  loggedInUser$: Observable<User | null> = this.userService.user$;

  constructor(private userService: UsersService) { }

  processLogout(): void {
    this.userService.logOut$();
  }
}

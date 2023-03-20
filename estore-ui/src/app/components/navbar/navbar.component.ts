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
  @Output() onSearchType: EventEmitter<string> = new EventEmitter();

  constructor(private userService: UsersService) { }

  searchBarChanged(value: string): void {
    this.onSearchType.emit(value);
  }

  processLogout(): void {
    this.userService.logOut$();
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CartItem } from 'src/app/interfaces/cart-item';
import { User } from 'src/app/interfaces/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent { 
  loggedInUser$: Observable<User> = this.userService.user$;

  constructor(private userService: UserService, public router: Router) { }

  totalCartItems(items: CartItem[]): number {
    return items.reduce((acc: number, val: CartItem) => acc + val.quantity, 0);
  }

  processLogout(): void {
    this.userService.logOut$();
  }
}

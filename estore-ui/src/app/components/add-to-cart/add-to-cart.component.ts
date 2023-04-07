import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-to-cart',
  templateUrl: './add-to-cart.component.html',
  styleUrls: ['./add-to-cart.component.css']
})
export class AddToCartComponent {
  loggedInUser$: Observable<User> = this.userService.user$;
  @Input() keyboard: Keyboard = <Keyboard>{};
  @Input() quantity: number = 1;

  constructor(private userService: UserService, private notificationService: NotificationService, private router: Router) { }

  async addToCart() {
    const user: User = await firstValueFrom(this.loggedInUser$);
    if (Object.keys(user).length === 0) {
      this.router.navigate(
        ['login'],
        { queryParams: { returnURL: this.router.url } }
      );
      return;
    }
    
    this.userService.addToCart$(user.id, this.keyboard.id, this.quantity)
      .subscribe({
        error: (err) => { 
          if ((err as HttpErrorResponse).status != HttpStatusCode.RangeNotSatisfiable) return;
          this.notificationService.postMessage("Purchase quantity exceeds amount in stock!");
        }
      });
  }
}

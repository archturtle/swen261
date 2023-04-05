import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { NotifcationService } from 'src/app/services/notifcation.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-add-to-cart',
  templateUrl: './add-to-cart.component.html',
  styleUrls: ['./add-to-cart.component.css']
})
export class AddToCartComponent {
  loggedInUser$: Observable<User> = this.usersService.user$;
  @Input() keyboard: Keyboard = <Keyboard>{};
  @Input() quantity: number = 1;

  constructor(private usersService: UsersService, private notificationService: NotifcationService, private router: Router) { }

  async addToCart() {
    const user: User = await firstValueFrom(this.loggedInUser$);
    if (Object.keys(user).length === 0) {
      this.router.navigate(
        ['login'],
        { queryParams: { returnURL: this.router.url } }
      );
      return;
    }
    
    this.usersService.addToCart$(user.id, this.keyboard.id, this.quantity)
      .subscribe(
        result => { },
        error => { 
          if ((error as HttpErrorResponse).status != HttpStatusCode.RangeNotSatisfiable) return;

          this.notificationService.emitError("Purchase quantity exceeds amount in stock!");
        }
      );
  }
}

import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Observable, firstValueFrom } from 'rxjs';
import { CartItem } from 'src/app/interfaces/cart-item';
import { User } from 'src/app/interfaces/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent {
  loggedInUser$: Observable<User> = this.userService.user$;
  @Input() cartItem!: CartItem;

  constructor(private userService: UserService) { }

  async quantityChanged(value: number) {
    const currentUser = await firstValueFrom(this.loggedInUser$);
    if (Object.keys(currentUser).length === 0) return;

    let difference = this.cartItem.quantity - value;
    if (difference === 0) return;
    if (difference < 0) {
      this.userService.addToCart$(currentUser.id, this.cartItem.keyboard.id, difference * -1)
        .subscribe();
    } else {
      this.userService.removeFromCart$(currentUser.id, this.cartItem.keyboard.id, difference)
        .subscribe();
    }
  }

  calculateItemPrice(): number {
    return this.cartItem.quantity * this.cartItem.keyboard.price;
  }
}

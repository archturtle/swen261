import { Component, Input, OnInit } from '@angular/core';
import { Observable, firstValueFrom } from 'rxjs';
import { CartItem, CartItemType } from 'src/app/interfaces/cart-item';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  loggedInUser$: Observable<User> = this.userService.user$;
  @Input() cartItem!: CartItem;
  associatedKeyboard: Keyboard = <Keyboard>{};
  cartItemTypes = CartItemType;

  get itemLoaded(): boolean {
    if (this.cartItem.cartItemType === CartItemType.CUSTOM_KEYBOARD) return true;
    return Object.keys(this.associatedKeyboard).length !== 0;
  }

  get outOfStock(): boolean {
    if (this.cartItem.cartItemType === CartItemType.CUSTOM_KEYBOARD) return false;
    return this.associatedKeyboard.quantity === 0 || this.cartItem.quantity > this.associatedKeyboard.quantity;
  }

  constructor(private userService: UserService, private keyboardService: KeyboardService) { }

  ngOnInit(): void {
    if (this.cartItem.cartItemType !== CartItemType.STANDARD_KEYBOARD) return;
    this.keyboardService.getKeyboardById$(this.cartItem.keyboardID!)
      .subscribe(data => this.associatedKeyboard = data);
  }

  async quantityChanged(value: number) {
    const user = await firstValueFrom(this.loggedInUser$);
    if (Object.keys(user).length === 0) return;

    if (this.cartItem.cartItemType === CartItemType.STANDARD_KEYBOARD) {
      let difference = this.cartItem.quantity - value;
      if (difference === 0) return;
      if (difference < 0) {
        this.userService.addToCart$(user.id, {
          cartItemType: this.cartItem.cartItemType,
          quantity: difference * -1,
          keyboardID: this.associatedKeyboard.id
        }).subscribe();
      } else {
        this.userService.removeFromCart$(user.id, {
          cartItemType: this.cartItem.cartItemType,
          quantity: difference,
          keyboardID: this.associatedKeyboard.id
        }).subscribe();
      }
    } else {
      this.userService.removeFromCart$(user.id, this.cartItem)
        .subscribe();
    }
  }

  calculateItemPrice(): number {
    return (this.cartItem.cartItemType === CartItemType.STANDARD_KEYBOARD) ? this.cartItem.quantity * this.associatedKeyboard.price : (this.cartItem.customKeyboard?.price ?? 0);
  }
}

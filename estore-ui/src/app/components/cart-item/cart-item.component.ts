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

    let difference = this.cartItem.quantity - value;
    if (difference === 0) return;

    // const data: CartItem = (this.cartItem.cartItemType === CartItemType.STANDARD_KEYBOARD) ? {
    //   cartItemType: this.cartItem.cartItemType,
    //   quantity: difference * ((difference < 0) ? -1 : 1),
    //   keyboardID: this.associatedKeyboard.id 
    // }

    const data: CartItem = this.cartItem;
    data.quantity = (difference < 0) ? (difference * -1) : difference;

    if (difference < 0) {
      this.userService.addToCart$(user.id, data).subscribe();
    } else {
      this.userService.removeFromCart$(user.id, data).subscribe();
    }
  }

  calculateItemPrice(): number {
    return this.cartItem.quantity * ((this.cartItem.cartItemType === CartItemType.STANDARD_KEYBOARD) ? 
                                      this.associatedKeyboard.price : 
                                      (this.cartItem.customKeyboard?.price ?? 0));
  }
}

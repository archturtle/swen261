import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin, map, Observable, of, switchMap } from 'rxjs';
import { CartItem, CartItemType } from 'src/app/interfaces/cart-item';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  loggedInUser$: Observable<User> = this.userService.user$;
  cartItems: CartItem[] = [];
  associatedKeyboards: Keyboard[] = [];

  constructor(private userService: UserService, private keyboardService: KeyboardService, private router: Router) { }

  ngOnInit(): void {
    // Check if user is admin/empty
    this.loggedInUser$.subscribe((user: User) => {
      if (Object.keys(user).length == 0 || user.role == 0) { this.router.navigate(['/']); return; }
    });

    // Get all cart items
    this.loggedInUser$.pipe(
      map((value: User): CartItem[] => value.cart)
    ).subscribe(data => this.cartItems = data);

    // Get associated keyboards
    this.loggedInUser$.pipe(
      map((value: User): CartItem[] => value.cart),
      map((value: CartItem[]) => value.filter(item => item.cartItemType === CartItemType.STANDARD_KEYBOARD)),
      switchMap((value: CartItem[]) => {
        if (value.length === 0) return of([]);
        return forkJoin(value.map(item => this.keyboardService.getKeyboardById$(item.keyboardID!)));
      })
    ).subscribe(data => this.associatedKeyboards = data);
  }

  private findKeyboardByID(id: number): Keyboard {
    let filteredKeyboards = this.associatedKeyboards.filter(i => i.id === id);
    if (filteredKeyboards.length === 0) return <Keyboard>{};

    return filteredKeyboards[0];
  }

  private isItemOutOfStock(value: CartItem): boolean {
    if (value.cartItemType === CartItemType.CUSTOM_KEYBOARD) return false;

    let keyboard = this.findKeyboardByID(value.keyboardID!); 
    if (Object.keys(keyboard).length === 0) return true;
    return keyboard.quantity === 0 || value.quantity > keyboard.quantity;
  }

  get anyOutOfStock(): boolean {
    return this.cartItems.filter(value => this.isItemOutOfStock(value)).length !== 0;
  }

  getTotalQuantity(): number {
    return this.cartItems.reduce((acc: number, val: CartItem) => {
      return this.isItemOutOfStock(val) ? acc : acc + val.quantity;
    }, 0);
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((acc: number, val: CartItem) => {
      if (this.isItemOutOfStock(val)) return acc;
      if (val.cartItemType === CartItemType.CUSTOM_KEYBOARD) return acc + (val.quantity * (val?.customKeyboard?.price ?? 0))
      
      let keyboard = this.findKeyboardByID(val.keyboardID!);
      return acc + (val.quantity * keyboard.price);
    }, 0);
  }

  shouldShowCheckout(): boolean {
    if (this.cartItems.length == 0) return false;
    return this.cartItems.filter(i => this.isItemOutOfStock(i)).length !== this.cartItems.length;
  }

  getInStockItems(): CartItem[] {
    if (this.cartItems.length === 0) return [];
    return this.cartItems.filter(i => !this.isItemOutOfStock(i));
  }
}

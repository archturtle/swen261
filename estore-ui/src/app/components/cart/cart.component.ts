import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { filter, firstValueFrom, forkJoin, map, Observable, of, switchMap } from 'rxjs';
import { CartItem } from 'src/app/interfaces/cart-item';
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
  cartItems$!: Observable<CartItem[]>;
  anyOutOfStock: boolean = false;

  constructor(private userService: UserService, private keyboardService: KeyboardService, private router: Router) { }

  ngOnInit(): void {
    this.cartItems$ = this.loggedInUser$.pipe(
      map((value: User): number[] => (Object.keys(value).length > 0) ? value.cart : []),
      map((values: number[]): Map<number, number> => {
        return values.reduce((acc: Map<number, number>, item: number) => {
          let res = acc.get(item);
          res ? acc.set(item, res + 1) : acc.set(item, 1);

          return acc;
        }, new Map());
      }),
      switchMap((cartIds: Map<number, number>) => {
        if ([...cartIds.keys()].length == 0) return forkJoin([of(new Map()), of([])]);

        return forkJoin([of(cartIds), forkJoin([...cartIds.keys()].map(id => this.keyboardService.getKeyboardById$(id)))])
      }),
      map(([cartIds, items]: [Map<number, number>, Keyboard[]]) => {
        return items.map((item: Keyboard): CartItem => {
          if (item.quantity === 0 || cartIds.get(item.id)! > item.quantity) this.anyOutOfStock = true;
          return { 
            keyboard: item, 
            quantity: cartIds.get(item.id)!, 
            outOfStock: (item.quantity === 0 || cartIds.get(item.id)! > item.quantity) 
          };
        });
      }),
      map((things: CartItem[]) => [...things].sort((a: CartItem, b: CartItem) => {
        if (a.keyboard.name > b.keyboard.name) return 1;
        if (a.keyboard.name < b.keyboard.name) return -1;

        return 0;
      })),
    )

    this.loggedInUser$.subscribe((user: User) => {
      if (Object.keys(user).length == 0 || user.role == 0) this.router.navigate(['/'])
    });
  }

  getTotalQuantity(values: CartItem[] | null): number {
    if (!values) return 0;
    return values.reduce((acc, val) => acc + val.quantity , 0)
  }

  getTotalPrice(values: CartItem[] | null): number {
    if (!values) return 0;
    return values.reduce((acc, val) => acc + (val.keyboard.price * val.quantity) , 0)
  }

  identifyCartItem(index: any, item: CartItem) {
    return item.quantity;
  }
}

import { Component, OnInit } from '@angular/core';
import { firstValueFrom, forkJoin, map, Observable, of, switchMap } from 'rxjs';
import { CartItem } from 'src/app/interfaces/cart-item';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  loggedInUser$: Observable<User | null> = this.usersService.user$;
  cartItems$!: Observable<CartItem[]>;

  constructor(private usersService: UsersService, private keyboardService: KeyboardService) { }

  ngOnInit(): void {
    this.cartItems$ = this.loggedInUser$.pipe(
      map((value: User | null): number[] => value ? value.cart : []),
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
          return { keyboard: item, quantity: cartIds.get(item.id!)! }
        });
      }),
      map((things: CartItem[]) => things.sort((a: CartItem, b: CartItem) => {
        if (a.keyboard.name > b.keyboard.name) return 1;
        if (a.keyboard.name < b.keyboard.name) return -1;

        return 0;
      })),
    )
  }

  async onQuantityChange(value: number, item: CartItem) {
    const currentUser = await firstValueFrom(this.loggedInUser$);
    if (!currentUser) return;

    let difference = item.quantity - value;
    if (difference == 0) return;
    if (difference < 0) {
      this.usersService.addToCart$(currentUser.id!, item.keyboard.id!, difference * -1)
        .subscribe();
    } else {
      this.usersService.removeFromCart$(currentUser.id!, item.keyboard.id!, difference)
        .subscribe();
    }
  }
}

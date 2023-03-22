import { Component, OnInit } from '@angular/core';
import { firstValueFrom, map, Observable, tap } from 'rxjs';
import { CartItem } from 'src/app/interfaces/cart-item';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  loggedInUser$: Observable<User | null> = this.usersService.user$;
  cartItems$!: Observable<CartItem[]>;

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    this.cartItems$ = this.loggedInUser$.pipe(
      map((value: User | null) => value ? value.cart : []),
      map((value: Keyboard[]) => {
        let map = new Map<number, CartItem>();
        value.forEach((item: Keyboard) => {
          let res: CartItem | undefined = map.get(item.id!);
          if (!res) {
            map.set(item.id!, { keyboard: item, quantity: 1 });
          } else {
            map.set(item.id!, { keyboard: item, quantity: res.quantity + 1 });
          };
        });

        return [...map.values()];
      })
    );
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

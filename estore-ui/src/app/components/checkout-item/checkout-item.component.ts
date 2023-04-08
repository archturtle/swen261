import { Component, Input, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { CartItem } from 'src/app/interfaces/cart-item';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { KeyboardService } from 'src/app/services/keyboard.service';

@Component({
  selector: 'app-checkout-item',
  templateUrl: './checkout-item.component.html',
  styleUrls: ['./checkout-item.component.css']
})
export class CheckoutItemComponent implements OnInit {
  @Input() item!: CartItem;
  associatedKeyboard: Keyboard = <Keyboard>{}
  get keyboardLoaded(): boolean {
    return Object.keys(this.associatedKeyboard).length !== 0;
  }

  constructor(private keyboardService: KeyboardService) { }

  ngOnInit(): void {
    this.keyboardService.keyboards$.pipe(
      map((keyboards: Keyboard[]): Keyboard => {
        const filtered = keyboards.filter(i => i.id === this.item.keyboardID);
        if (filtered.length === 0) return <Keyboard>{};

        return filtered[0];
      })
    ).subscribe(data => this.associatedKeyboard = data);
  }

  get itemPrice(): number {
    return this.item.quantity * this.associatedKeyboard.price;
  }
}

import { Component, Input } from '@angular/core';
import { CartItem } from 'src/app/interfaces/cart-item';

@Component({
  selector: 'app-checkout-item',
  templateUrl: './checkout-item.component.html',
  styleUrls: ['./checkout-item.component.css']
})
export class CheckoutItemComponent {
  @Input() item!: CartItem;

  calculateItemPrice(value: CartItem): number {
    return value.quantity * value.keyboard.price;
  }
}

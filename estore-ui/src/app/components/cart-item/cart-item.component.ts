import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CartItem } from 'src/app/interfaces/cart-item';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent {
  @Input() cartItem!: CartItem;
  @Output() onQuantityChanged: EventEmitter<number> = new EventEmitter<number>();

  quantityChanged(value: number) {
    this.onQuantityChanged.emit(value);
  }
}

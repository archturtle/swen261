import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CartItem } from 'src/app/interfaces/cart-item';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent {
  @Output() quantityModified: EventEmitter<number> = new EventEmitter<number>();
  @Input() item: CartItem = <CartItem>{};

  onChange(item: number) {
    this.quantityModified.emit(item);
  }

  deleteClicked() {
    this.quantityModified.emit(0);
  }
}

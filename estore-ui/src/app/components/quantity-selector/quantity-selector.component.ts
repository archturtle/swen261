import { Component, ElementRef, EventEmitter, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';

@Component({
  selector: 'app-quantity-selector',
  templateUrl: './quantity-selector.component.html',
  styleUrls: ['./quantity-selector.component.css']
})
export class QuantitySelectorComponent {
  @ViewChild('quantitySelector') quantitySelector!: ElementRef<HTMLSelectElement>;
  @Input() maxQuantity: number = 1;
  @Input() currentlySelectedIndex: number = 0;
  @Output() quantityChanged: EventEmitter<number> = new EventEmitter<number>();

  decrementQuantity(): void {
    if ((this.currentlySelectedIndex - 1) < 0) return;

    this.currentlySelectedIndex--;
    this.quantitySelector.nativeElement.selectedIndex = this.currentlySelectedIndex;
    this.quantityChanged.emit(parseInt(this.quantitySelector.nativeElement.value));
  }

  quantitySelected(): void {
    this.currentlySelectedIndex = this.quantitySelector.nativeElement.selectedIndex;
    this.quantityChanged.emit(parseInt(this.quantitySelector.nativeElement.value));
  }

  incrementQuantity(): void {
    if ((this.currentlySelectedIndex + 1) >= this.maxQuantity) return;

    this.currentlySelectedIndex++;
    this.quantitySelector.nativeElement.selectedIndex = this.currentlySelectedIndex;
    this.quantityChanged.emit(parseInt(this.quantitySelector.nativeElement.value));
  }
}

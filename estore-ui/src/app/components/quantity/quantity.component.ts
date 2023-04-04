import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-quantity',
  templateUrl: './quantity.component.html',
  styleUrls: ['./quantity.component.css']
})
export class QuantityComponent {
  @Output() quantityModified: EventEmitter<number> = new EventEmitter<number>();
  @Input() quantity!: number;
  @Input() defaultValue!: number;

  onChange(e: any): void {
    this.quantityModified.emit(parseInt(e.target.value))
  }
}

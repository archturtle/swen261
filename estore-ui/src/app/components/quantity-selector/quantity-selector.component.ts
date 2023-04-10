import { Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-quantity-selector',
  templateUrl: './quantity-selector.component.html',
  styleUrls: ['./quantity-selector.component.css']
})
export class QuantitySelectorComponent implements OnInit {
  // Input/Output
  @Input() maxQuantity: number = 1;
  @Input() initialQuantity: number = 1;
  @Output() quantityChanged: EventEmitter<number> = new EventEmitter<number>();

  // Forms
  quantityForm = this.formBuilder.group({
    selectedQuantity: ['1']
  });

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.quantityForm.patchValue({
      selectedQuantity: this.initialQuantity.toString()
    })
  }

  get selectedQuantity() { return this.quantityForm.get('selectedQuantity'); }

  decrementQuantity(): void {
    let value = this.selectedQuantity?.value;
    if (!value || parseInt(value) == 1) return;

    this.quantityForm.patchValue({
      selectedQuantity: (parseInt(value) - 1).toString()
    });
    this.quantityChanged.emit(parseInt(value) - 1);
  }

  quantitySelected(): void {
    let value = this.selectedQuantity?.value;
    if (!value) return;

    this.quantityChanged.emit(parseInt(value));
  }

  incrementQuantity(): void {
    let value = this.selectedQuantity?.value;
    if (!value || parseInt(value) == this.maxQuantity) return;

    this.quantityForm.patchValue({
      selectedQuantity: (parseInt(value) + 1).toString() 
    });
    this.quantityChanged.emit(parseInt(value) + 1);
  }
}

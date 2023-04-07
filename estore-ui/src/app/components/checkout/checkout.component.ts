import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/interfaces/cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  private validationErrorString: { [name: string]: string } = {
    firstName: 'First name is required and longer than 1 character.',
    lastName: 'Last name is required and longer than 1 character.',
    address: 'Address is required and longer than 1 character.',
    city: 'City is required and longer than 1 character.',
    state: 'State is required and must be 2 characters.',
    country: 'Country is required and longer than 1 character.',
    zipCode: 'Zip code is required and must be 5 numbers.',
    email: 'Email is required and must be valid.',
    phoneNumber: 'Phone Number is required and must be valid.',
    creditCardNumber: 'Card Number is required and must be 16 digits.',
    creditCardCVC: 'CVC is required and must be 3 digits (>100, <999).',
    creditCardHolder: 'Card Holder is required and longer than 1 character.',
    creditCardExpiration: 'Card Expiration is required and must be in the format MM/YY.',
    creditCardZipCode: 'Card zip code is required and must be 5 numbers.',
  }

  cartItems: CartItem[] = [];
  informationForm: FormGroup = this.formBuilder.group({
    firstName: ['', [Validators.required, Validators.minLength(1)]],
    lastName: ['', [Validators.required, Validators.minLength(1)]],
    address: ['', [Validators.required, Validators.minLength(1)]],
    city: ['', [Validators.required, Validators.minLength(1)]],
    state: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]],
    country: ['', [Validators.required, Validators.minLength(1)]],
    zipCode: ['', [Validators.required, Validators.pattern("^[0-9]{5}$")]],
    email: ['', [Validators.required, Validators.email]],
    phoneNumber: ['', [Validators.pattern('^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$')]],
    creditCardNumber: ['', [Validators.required, Validators.pattern("^[0-9]{16}$")]],
    creditCardCVC: ['', [Validators.required, Validators.min(100), Validators.max(999), Validators.pattern("^[0-9]{3}$")]],
    creditCardHolder: ['', [Validators.required, Validators.minLength(1)]],
    creditCardExpiration: ['', [Validators.required, Validators.pattern("^[0-9]{2}\/[0-9]{2}$")]],
    creditCardZipCode: ['', [Validators.required, Validators.pattern("^[0-9]{5}$")]],
  });
  validationErrors: string[] = [];

  constructor(private formBuilder: FormBuilder, private router: Router) {
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state == null) return;

    this.cartItems = state['cart']; 
  }

  getTotalPrice(values: CartItem[]): number {
    return values.reduce((acc, val) => acc + (val.keyboard.price * val.quantity) , 0)
  }

  proceedWithCheckout(): void {
    this.validationErrors = [];
    if (!this.informationForm.valid) {
      Object.keys(this.informationForm.controls).forEach(controlKey => {
        if (!this.informationForm.get(controlKey)?.valid) {
          this.validationErrors.push(this.validationErrorString[controlKey])
        }
      })
    }
  }
}

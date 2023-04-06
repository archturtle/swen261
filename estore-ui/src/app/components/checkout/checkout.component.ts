import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/interfaces/cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  cartItems: CartItem[] = [];
  informationForm: FormGroup = this.formBuilder.group({
    shipping: this.formBuilder.group({
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      country: '',
      state: '',
      zipCode: '',
      email: '',
      phoneNumber: ''
    }),
    payment: this.formBuilder.group({
      creditCardNumber: '',
      creditCardCVC: '',
      creditCardHolder: '',
      creditCardExpiration: '',
      creditCardZipCode: ''
    })
  });

  constructor(private formBuilder: FormBuilder, private router: Router) {
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state == null) return;

    this.cartItems = state['cart']; 
    console.log(this.cartItems);
  }

  getTotalPrice(values: CartItem[]): number {
    return values.reduce((acc, val) => acc + (val.keyboard.price * val.quantity) , 0)
  }

}

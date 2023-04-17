import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, firstValueFrom, forkJoin, map } from 'rxjs';
import { CartItem, CartItemType } from 'src/app/interfaces/cart-item';
import { CheckoutData } from 'src/app/interfaces/checkout-data';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { CheckoutService } from 'src/app/services/checkout.service';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  private currentUserID!: number;
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

  loggedInUser$: Observable<User> = this.userService.user$;
  cartItems: CartItem[] = [];
  associatedKeyboards: Keyboard[] = [];
  validationErrors: string[] = [];
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
  get firstName() { return this.informationForm.get('firstName') }
  get lastName() { return this.informationForm.get('lastName') }
  get address() { return this.informationForm.get('address') }
  get city() { return this.informationForm.get('city') }
  get state() { return this.informationForm.get('state') }
  get country() { return this.informationForm.get('country') }
  get zipCode() { return this.informationForm.get('zipCode') }
  get email() { return this.informationForm.get('email') }
  get phoneNumber() { return this.informationForm.get('phoneNumber') }
  get creditCardNumber() { return this.informationForm.get('creditCardNumber') }
  get creditCardCVC() { return this.informationForm.get('creditCardCVC') }
  get creditCardHolder() { return this.informationForm.get('creditCardHolder') }
  get creditCardExpiration() { return this.informationForm.get('creditCardExpiration') }
  get creditCardZipCode() { return this.informationForm.get('creditCardZipCode') }

  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService, private checkoutService: CheckoutService, private keyboardService: KeyboardService, private notificationService: NotificationService) {
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state == null) return;

    this.cartItems = state['cart']; 
  }

  ngOnInit(): void {
    this.loggedInUser$.subscribe((user: User) => {
      if (Object.keys(user).length == 0 || user.role == 0) this.router.navigate(['/']);
      this.currentUserID = user.id;
    });

    this.informationForm.valueChanges.subscribe(() => {
      this.validationErrors = [];
      if (!this.informationForm.valid) {
        Object.keys(this.informationForm.controls).forEach(controlKey => {
          if (!this.informationForm.get(controlKey)?.valid) {
            this.validationErrors.push(this.validationErrorString[controlKey])
          }
        })
  
        return;
      }
    });
    
    forkJoin(
      this.cartItems.filter(i => i.cartItemType === CartItemType.STANDARD_KEYBOARD)
        .map(i => this.keyboardService.getKeyboardById$(i.keyboardID!))
    ).subscribe(data => this.associatedKeyboards = data);
  }

  private getKeyboardByID(id: number): Keyboard {
    const filteredKeyboards = this.associatedKeyboards.filter(i => i.id === id);
    if (filteredKeyboards.length === 0) return <Keyboard>{};
    return filteredKeyboards[0];
  }

  get totalPrice(): number {
    return this.cartItems.reduce((acc: number, val: CartItem) => {
      if (val.cartItemType === CartItemType.CUSTOM_KEYBOARD) return acc + (val.quantity * (val.customKeyboard?.price ?? 0));
      let keyboard = this.getKeyboardByID(val.keyboardID!);
      if (Object.keys(keyboard).length === 0) return acc;

      return acc + (val.quantity * keyboard.price);
    }, 0);
  }

  async proceedWithCheckout() {
    if (!this.informationForm.valid) return;

    const checkoutData: CheckoutData = {
      userID: this.currentUserID,
      firstName: this.firstName?.value ?? "",
      lastName: this.lastName?.value ?? "",
      address: this.address?.value ?? "",
      city: this.city?.value ?? "",
      state: this.state?.value ?? "",
      country: this.country?.value ?? "",
      zipCode: parseInt(this.zipCode?.value ?? 0),
      email: this.email?.value ?? "",
      phoneNumber: this.phoneNumber?.value ?? "",
      creditCardNumber: this.creditCardNumber?.value ?? "",
      creditCardCVC: parseInt(this.creditCardCVC?.value ?? 0),
      creditCardHolder: this.creditCardHolder?.value ?? "",
      creditCardExpiration: this.creditCardExpiration?.value ?? "",
      creditCardZipCode: parseInt(this.creditCardZipCode?.value ?? 0)
    }

    let user = await firstValueFrom(this.checkoutService.checkout$(checkoutData));
    user = await firstValueFrom(this.userService.getUserById$(user.id)); 

    this.router.navigate(['/checkout/success']);
  }
}

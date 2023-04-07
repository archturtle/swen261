import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { ClientComponent } from './components/client/client.component';
import { LoginComponent } from './components/login/login.component';
import { KeyboardDetailComponent } from './components/keyboard-detail/keyboard-detail.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { CheckoutSuccessComponent } from './components/checkout-success/checkout-success.component';

const routes: Routes = [
  { path: '', component: ClientComponent },
  { path: 'login', component: LoginComponent },
  { path: 'keyboard/:id', component: KeyboardDetailComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'checkout/success', component: CheckoutSuccessComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
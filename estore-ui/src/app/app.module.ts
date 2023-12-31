import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { ClientComponent } from './components/client/client.component';
import { KeyboardComponent } from './components/keyboard/keyboard.component';
import { SearchComponent } from './components/search/search.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { KeyboardListComponent } from './components/keyboard-list/keyboard-list.component';
import { LoginComponent } from './components/login/login.component';
import { EditorComponent } from './components/editor/editor.component';
import { KeyboardDetailComponent } from './components/keyboard-detail/keyboard-detail.component';
import { AddToCartComponent } from './components/add-to-cart/add-to-cart.component';
import { CartComponent } from './components/cart/cart.component';
import { NotNullOrEmptyPipe } from './pipes/not-null-or-empty.pipe';
import { MessagePopupComponent } from './components/message-popup/message-popup.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { QuantitySelectorComponent } from './components/quantity-selector/quantity-selector.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { CheckoutItemComponent } from './components/checkout-item/checkout-item.component';
import { CheckoutSuccessComponent } from './components/checkout-success/checkout-success.component';
import { KeyboardConfiguratorComponent } from './components/keyboard-configurator/keyboard-configurator.component';
import { SegmentedControlComponent } from './components/segmented-control/segmented-control.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    KeyboardComponent,
    SearchComponent,
    NavbarComponent,
    KeyboardListComponent,
    LoginComponent,
    EditorComponent,
    KeyboardDetailComponent,
    AddToCartComponent,
    CartComponent,
    NotNullOrEmptyPipe,
    MessagePopupComponent,
    CheckoutComponent,
    QuantitySelectorComponent,
    CartItemComponent,
    CheckoutItemComponent,
    CheckoutSuccessComponent,
    KeyboardConfiguratorComponent,
    SegmentedControlComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
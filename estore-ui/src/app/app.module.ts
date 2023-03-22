import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KeyboardDetailComponent } from './keyboard-detail/keyboard-detail.component';
import { KeyboardsComponent } from './keyboards/keyboards.component';
import { KeyboardSearchComponent } from './keyboard-search/keyboard-search.component';
import { MessagesComponent } from './messages/messages.component';
import { KeyboardCartComponent } from './keyboard-cart/keyboard-cart.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    KeyboardsComponent,
    KeyboardDetailComponent,
    MessagesComponent,
    KeyboardSearchComponent,
    KeyboardCartComponent,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
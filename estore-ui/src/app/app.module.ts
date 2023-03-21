import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './components/client/client.component';
import { ProductComponent } from './components/product/product.component';
import { SearchComponent } from './components/search/search.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { LoginComponent } from './components/login/login.component';
import { EditorComponent } from './components/editor/editor.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { AddToCartComponent } from './components/add-to-cart/add-to-cart.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    ProductComponent,
    SearchComponent,
    NavbarComponent,
    ProductListComponent,
    LoginComponent,
    EditorComponent,
    ProductDetailComponent,
    AddToCartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

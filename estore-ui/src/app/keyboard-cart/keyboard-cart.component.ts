import { Component, OnInit } from '@angular/core';
import { Observable, Subject} from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Keyboard } from '../keyboard';
import { KeyboardService } from '../keyboard.service';

@Component({
  selector: 'app-keyboard-cart',
  templateUrl: './keyboard-cart.component.html',
  styleUrls: ['./keyboard-cart.component.css']
})
export class KeyboardCartComponent implements OnInit {
  shoppingcart: Keyboard[] = []; //shopping cart is an empty array of keyboards

  //need to implement a shoppingcart/user service for further code but i will write skeleton
  //need to implement a new service for shopping cart thats empty
  // treating shopping cart as a simple list of keyboards for now
  constructor(private keyboardService: KeyboardService){

  }

  ngOnInit(): void{
    this.getCart();
  }

  getCart(): void {
    /**
     * this.cartService.getCart().subscribe(shoppingcart => this.shoppingcart = shoppingcart);
     */
    throw new Error('Method not implemented.');
  }

  add(name: string): void {
    /**
     * name = name.trim()
     * if(!name){ return; }
     * this.cartService.addKeyboard({name} as Keyboard).suscribe(shoppingcart => {this.shoppingcart.push(shoppingcart)})
     */
    throw new Error('Method not implemented');
  }

  edit(): void {
    /**
     * this simply edits quantity of keyboard being bought
     * take inspiration from tour of hero name edit
     */
    throw new Error('Method not implemented.')
  }

  delete(): void {
    /**
     * this.shoppingcart = this.shoppingcart.filter(k => k !== shoppingcart)
     * this.cartService.deleteKeyboard(keyboard.id).suscribe();
     */
    throw new Error('Method not implemented.')
  }


}

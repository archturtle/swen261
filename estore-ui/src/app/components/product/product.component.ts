import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { ProductsService } from 'src/app/services/products.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  loggedInUser$: Observable<User | null> = this.usersService.user$;
  @Input() product!: Keyboard

  constructor(private usersService: UsersService, private productsService: ProductsService) {  }

  ngOnInit(): void { }

  removeProduct(): void {
    this.productsService.deleteProduct$(this.product.id)
      .subscribe();
  }
}

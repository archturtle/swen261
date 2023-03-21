import { Component, Input, OnInit } from '@angular/core';
import { firstValueFrom, lastValueFrom, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { NotifcationService } from 'src/app/services/notifcation.service';
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

  constructor(private usersService: UsersService, private productsService: ProductsService, private notificationService: NotifcationService) {  }

  ngOnInit(): void { }

  removeProduct(): void {
    if (!this.product.id) return;

    this.productsService.deleteProduct$(this.product.id)
      .subscribe();
  }

  async productClicked() {
    console.log(this.loggedInUser$)
    const user: User | null = await firstValueFrom(this.loggedInUser$);
    if (!user || user.role != 0) {
      return;
    }

    this.notificationService.changeProduct(this.product);
  }
}

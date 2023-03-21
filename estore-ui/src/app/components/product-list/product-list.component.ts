import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { filter, map, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { NotifcationService } from 'src/app/services/notifcation.service';
import { ProductsService } from 'src/app/services/products.service';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnChanges {
  private products$: Observable<Keyboard[]> = this.productService.products$;
  filteredProducts$: Observable<Keyboard[]> = this.productService.products$;

  constructor(private productService: ProductsService, private notificationService: NotifcationService) { }

  ngOnInit(): void { 
    this.productService.getProducts$(null)
      .subscribe();

    this.notificationService.searchStringChanged
      .subscribe((data: string) => {
        this.filteredProducts$ = this.products$.pipe(
          map(result => {
            return result.filter(item => item.name.toLowerCase().startsWith(data))
          })
        );
      });
  }

  ngOnChanges(changes: SimpleChanges): void {
  }
}

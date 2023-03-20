import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { filter, map, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { ProductsService } from 'src/app/services/products.service';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnChanges {
  private products$: Observable<Keyboard[]> = this.productService.products$;
  filteredProducts$: Observable<Keyboard[]> = this.productService.products$;
  @Input() searchString!: string;

  constructor(private productService: ProductsService) { }

  ngOnInit(): void { 
    this.productService.getProducts$(null)
      .subscribe();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.filteredProducts$ = this.products$.pipe(
      map(result => {
        return result.filter(item => item.name.toLowerCase().startsWith(changes['searchString'].currentValue))
      })
    );
  }
}

import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { ProductsService } from 'src/app/services/products.service';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnChanges {
  private products!: Keyboard[];
  filteredProducts!: Keyboard[];
  @Input() searchString!: string;

  constructor(private productService: ProductsService) { }

  ngOnInit(): void {
    this.productService.getProducts$(null)
      .subscribe(items => {
        this.products = items;
        this.filteredProducts = items;
      });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (!this.products || !this.filteredProducts) return;

    this.filteredProducts = this.products.filter((item: Keyboard) => {
      return item.name.toLowerCase().startsWith(changes['searchString'].currentValue);
    });
  }
}

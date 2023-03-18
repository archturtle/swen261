import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  products!: Keyboard[];

  constructor(private productService: ProductsService) { }

  ngOnInit(): void {
    this.productService.getProducts$(null)
      .subscribe(items => this.products = items);
  }
}

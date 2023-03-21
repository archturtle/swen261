import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product!: Keyboard;

  constructor(private route: ActivatedRoute, private productService: ProductsService) { }

  ngOnInit(): void {
    const id: string | null = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.productService.getProductById$(parseInt(id))
      .subscribe((data: Keyboard) => this.product = data);
  }
}

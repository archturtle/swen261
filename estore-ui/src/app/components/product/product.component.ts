import { Component, Input, OnInit } from '@angular/core';
import { Keyboard } from 'src/app/interfaces/keyboard';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() product!: Keyboard

  ngOnInit(): void { }
}

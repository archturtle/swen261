import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { NotifcationService } from 'src/app/services/notifcation.service';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {
  editGroup: FormGroup = this.formBuilder.group({
    id: { value: '', disabled: true },
    name: [''],
    price: [''],
    quantity: ['']
  });
  addProduct: boolean = true;

  constructor(private formBuilder: FormBuilder, private productService: ProductsService, private notificationService: NotifcationService) {  }

  ngOnInit(): void {
    this.notificationService.productSelected
      .subscribe((data: Keyboard | null) => {
        if (!data) return;

        this.editGroup.patchValue({
          id: data?.id?.toString(),
          name: data.name,
          price: data.price.toString(),
          quantity: data.quantity.toString()
        });

        this.addProduct = false;
      });
  }

  clearForm(): void {
    this.addProduct = true;
    this.notificationService.changeProduct(null);
  }

  submitForm(): void {
    const product: Keyboard = { 
      ...(this.editGroup.get<string>('id')?.value?.length > 0 && { id: parseInt(this.editGroup.get('id')?.value) }),
      name: this.editGroup.get('name')?.value,
      price: parseFloat(this.editGroup.get('price')?.value),
      quantity: parseInt(this.editGroup.get('quantity')?.value) > 0 ? parseInt(this.editGroup.get('quantity')?.value) : 0 
    }

    if (this.addProduct) {
      this.productService.addProduct$(product)
        .subscribe();
    } else {
      this.productService.updateProduct$(product)
        .subscribe();
    }
  }
}

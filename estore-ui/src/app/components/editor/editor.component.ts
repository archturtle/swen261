import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { NotifcationService } from 'src/app/services/notifcation.service';
import { KeyboardService } from 'src/app/services/keyboard.service';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {
  editGroup: FormGroup = this.formBuilder.group({
    id: { value: '', disabled: true },
    name: ['', [Validators.required, Validators.minLength(5)]],
    price: [0, [Validators.required, Validators.min(1)]],
    quantity: [0, [Validators.required, Validators.min(1)]]
  });
  addKeyboard: boolean = true;
  edited: boolean = false;

  constructor(private formBuilder: FormBuilder, private keyboardService: KeyboardService, private notificationService: NotifcationService) {  }

  ngOnInit(): void {
    this.notificationService.keyboardSelected
      .subscribe((data: Keyboard) => {
        if (Object.keys(data).length === 0) return;

        this.editGroup.patchValue({
          id: data?.id?.toString(),
          name: data.name,
          price: data.price.toString(),
          quantity: data.quantity.toString()
        });

        this.addKeyboard = false;
      });
    
    this.editGroup.valueChanges.subscribe(() => {
      this.edited = true;
    });
  }

  get id() {
    return this.editGroup.controls['id'];
  }

  get name() {
    return this.editGroup.controls['name'];
  }

  get price() {
    return this.editGroup.controls['price'];
  }

  get quantity() {
    return this.editGroup.controls['quantity'];
  }

  clearForm(): void {
    this.addKeyboard = true;
    this.notificationService.changeKeyboard(<Keyboard>{});
  }

  submitForm(): void {
    if (!this.editGroup.valid) return;

    const keyboard: Keyboard = { 
      id: parseInt(this.id?.value) ?? 0,
      name: this.name?.value,
      price: parseFloat(this.price?.value),
      description: '',
      quantity: parseInt(this.quantity?.value) > 0 ? parseInt(this.quantity?.value) : 0 
    }

    if (this.addKeyboard) {
      this.keyboardService.addKeyboard$(keyboard)
        .subscribe();
    } else {
      this.keyboardService.updateKeyboard$(keyboard)
        .subscribe();
    }
  }
}

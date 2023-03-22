import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    name: [''],
    price: [''],
    quantity: ['']
  });
  addKeyboard: boolean = true;

  constructor(private formBuilder: FormBuilder, private keyboardService: KeyboardService, private notificationService: NotifcationService) {  }

  ngOnInit(): void {
    this.notificationService.keyboardSelected
      .subscribe((data: Keyboard | null) => {
        if (!data) return;

        this.editGroup.patchValue({
          id: data?.id?.toString(),
          name: data.name,
          price: data.price.toString(),
          quantity: data.quantity.toString()
        });

        this.addKeyboard = false;
      });
  }

  clearForm(): void {
    this.addKeyboard = true;
    this.notificationService.changeKeyboard(null);
  }

  submitForm(): void {
    const keyboard: Keyboard = { 
      ...(this.editGroup.get<string>('id')?.value?.length > 0 && { id: parseInt(this.editGroup.get('id')?.value) }),
      name: this.editGroup.get('name')?.value,
      price: parseFloat(this.editGroup.get('price')?.value),
      quantity: parseInt(this.editGroup.get('quantity')?.value) > 0 ? parseInt(this.editGroup.get('quantity')?.value) : 0 
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

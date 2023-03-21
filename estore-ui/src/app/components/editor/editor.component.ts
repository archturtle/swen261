import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent {
  editGroup: FormGroup = this.formBuilder.group({
    name: [''],
    price: [''],
    quantity: ['']
  });

  constructor(private formBuilder: FormBuilder) {  }
}

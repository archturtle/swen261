import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup = this.formBuilder.group({
    username: ['']
  });

  constructor(private formBuilder: FormBuilder) {  }

  loginFormSubmitted(): void {
    console.log(this.loginForm.value['username'])
  }
}

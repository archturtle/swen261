import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup = this.formBuilder.group({
    username: ['']
  });
  // hideError: boolean = true;

  constructor(private router: Router, private formBuilder: FormBuilder, private usersService: UsersService) {  }

  async loginFormSubmitted() {
    const username = this.loginForm.value['username'];
    let resp = await firstValueFrom(this.usersService.getUser$(username))
    if (resp == null) {
      const user: User = { name: username, role: 1, cart: [] }
      resp = await firstValueFrom(this.usersService.createUser$(user));
    }

    localStorage.setItem("user", resp.id?.toString()!);
    this.router.navigate(['/']);
  }
}

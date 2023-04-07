import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, firstValueFrom } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { UserService } from 'src/app/services/user.service';

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

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder, private UserService: UserService) {  }

  async loginFormSubmitted() {
    const username = this.loginForm.value['username'];
    if (username.trim().length == 0) return;

    let resp = await firstValueFrom(this.UserService.getUser$(username))
    if (Object.keys(resp).length === 0) {
      const user: User = { id: 0, name: username, role: 1, cart: [] }
      resp = await firstValueFrom(this.UserService.createUser$(user));
    }

    const queryParams = await firstValueFrom(this.activatedRoute.queryParams
      .pipe(filter(params => params['returnURL'])));

    localStorage.setItem("user", resp.id.toString());
    this.router.navigate([queryParams['returnURL']]);
  }
}

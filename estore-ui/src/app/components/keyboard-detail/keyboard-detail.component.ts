import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-keyboard-detail',
  templateUrl: './keyboard-detail.component.html',
  styleUrls: ['./keyboard-detail.component.css']
})
export class KeyboardDetailComponent implements OnInit {
  loggedInUser$: Observable<User> = this.userService.user$;
  keyboard: Keyboard = <Keyboard>{};
  selectedValue!: Event;
  quantity: number = 1;

  constructor(private router: Router, private route: ActivatedRoute, private keyboardService: KeyboardService, private userService: UserService) { }

  ngOnInit(): void {
    const id: string | null = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.keyboardService.getKeyboardById$(parseInt(id))
      .subscribe((data: Keyboard) => this.keyboard = data);

    this.loggedInUser$.subscribe((value: User) => {
      if (Object.keys(value).length === 0 || value.role == 1) return;

      this.router.navigate(['/']);
    })
  }

  handleQuantityChange(value: number): void {
    this.quantity = value;
  }
}

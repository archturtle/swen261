import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { NotifcationService } from 'src/app/services/notifcation.service';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-keyboard',
  templateUrl: './keyboard.component.html',
  styleUrls: ['./keyboard.component.css']
})
export class KeyboardComponent implements OnInit {
  loggedInUser$: Observable<User> = this.usersService.user$;
  @Input() keyboard: Keyboard = <Keyboard>{};
  isSelected: boolean = false;

  constructor(private usersService: UsersService, 
              private keyboardService: KeyboardService, 
              private notificationService: NotifcationService, 
              private router: Router) {  }

  ngOnInit(): void {
    this.notificationService.keyboardSelected
      .subscribe((value: Keyboard) => {
        this.isSelected = (Object.keys(value).length === 0) ? false : value.id == this.keyboard.id;
      });
  }

  removeKeyboard(): void {
    if (Object.keys(this.keyboard).length === 0) return;

    this.keyboardService.deleteKeyboard$(this.keyboard.id)
      .subscribe();
  }

  async keyboardClicked() {
    const user: User = await firstValueFrom(this.loggedInUser$);
    if (Object.keys(user).length === 0 || user.role != 0) {
      this.router.navigate(['keyboard', this.keyboard.id])
      return;
    }
    
    this.notificationService.changeKeyboard(this.keyboard);
  }
}

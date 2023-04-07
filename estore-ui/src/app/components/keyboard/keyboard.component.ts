import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { User } from 'src/app/interfaces/user';
import { NotificationService } from 'src/app/services/notification.service';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-keyboard',
  templateUrl: './keyboard.component.html',
  styleUrls: ['./keyboard.component.css']
})
export class KeyboardComponent implements OnInit {
  loggedInUser$: Observable<User> = this.UserService.user$;
  isSelected: boolean = false;
  @Input() keyboard: Keyboard = <Keyboard>{};

  constructor(private UserService: UserService, 
              private keyboardService: KeyboardService, 
              private notificationService: NotificationService, 
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

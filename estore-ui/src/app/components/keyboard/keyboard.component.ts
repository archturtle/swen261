import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom, lastValueFrom, Observable } from 'rxjs';
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
  loggedInUser$: Observable<User | null> = this.usersService.user$;
  @Input() keyboard!: Keyboard
  isSelected: boolean = false;

  constructor(private usersService: UsersService, 
              private keyboardService: KeyboardService, 
              private notificationService: NotifcationService, 
              private router: Router) {  }

  ngOnInit(): void {
    this.notificationService.keyboardSelected
      .subscribe((value: Keyboard | null) => {
        this.isSelected = (!value) ? false : value.id == this.keyboard.id;
      });
  }

  removeKeyboard(): void {
    if (!this.keyboard.id) return;

    this.keyboardService.deleteKeyboard$(this.keyboard.id)
      .subscribe();
  }

  async keyboardClicked() {
    const user: User | null = await firstValueFrom(this.loggedInUser$);
    if (!user || user.role != 0) {
      this.router.navigate(['keyboard', this.keyboard.id])
      return;
    }
    
    this.notificationService.changeKeyboard(this.keyboard);
  }
}

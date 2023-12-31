import { Component } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { NotificationService } from 'src/app/services/notification.service';
import { KeyboardService } from 'src/app/services/keyboard.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/interfaces/user';


@Component({
  selector: 'app-keyboard-list',
  templateUrl: './keyboard-list.component.html',
  styleUrls: ['./keyboard-list.component.css']
})
export class KeyboardListComponent {
  private keyboards$: Observable<Keyboard[]> = this.keyboardService.keyboards$;
  filteredKeyboards$: Observable<Keyboard[]> = this.keyboardService.keyboards$;
  loggedInUser$: Observable<User> = this.userService.user$;
  searchBoxEmpty: boolean = true;

  constructor(private keyboardService: KeyboardService, private userService: UserService, private notificationService: NotificationService) { }

  ngOnInit(): void { 
    this.keyboardService.getKeyboards$()
      .subscribe();

    this.notificationService.searchStringChanged
      .subscribe((data: string) => {
        this.searchBoxEmpty = (data.trim().length === 0);
        this.filteredKeyboards$ = this.keyboards$.pipe(
          map(result => {
            return result.filter(item => item.name.toLowerCase().startsWith(data.trim().toLowerCase()))
          })
        );
      });
  }
}

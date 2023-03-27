import { Component } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { NotifcationService } from 'src/app/services/notifcation.service';
import { KeyboardService } from 'src/app/services/keyboard.service';


@Component({
  selector: 'app-keyboard-list',
  templateUrl: './keyboard-list.component.html',
  styleUrls: ['./keyboard-list.component.css']
})
export class KeyboardListComponent {
  private keyboards$: Observable<Keyboard[]> = this.keyboardService.keyboards$;
  filteredKeyboards$: Observable<Keyboard[]> = this.keyboardService.keyboards$;

  constructor(private keyboardService: KeyboardService, private notificationService: NotifcationService) { }

  ngOnInit(): void { 
    this.keyboardService.getKeyboards$()
      .subscribe();

    this.notificationService.searchStringChanged
      .subscribe((data: string) => {
        this.filteredKeyboards$ = this.keyboards$.pipe(
          map(result => {
            return result.filter(item => item.name.toLowerCase().startsWith(data.toLowerCase()))
          })
        );
      });
  }
}

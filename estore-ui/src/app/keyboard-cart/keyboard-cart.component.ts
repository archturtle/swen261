import { Component, OnInit } from '@angular/core';
import { Observable, Subject} from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Keyboard } from '../keyboard';
import { KeyboardService } from '../keyboard.service';

@Component({
  selector: 'app-keyboard-cart',
  templateUrl: './keyboard-cart.component.html',
  styleUrls: ['./keyboard-cart.component.css']
})
export class KeyboardCartComponent {

}

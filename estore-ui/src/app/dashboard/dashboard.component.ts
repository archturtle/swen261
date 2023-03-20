import { Component, OnInit } from '@angular/core';
import { Keyboard } from '../keyboard';
import { KeyboardService } from '../keyboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})

export class DashboardComponent implements OnInit {
  keyboards: Keyboard[] = [];

  constructor(private keyboardService: KeyboardService) { }

  ngOnInit(): void {
    this.getKeyboards();
  }

  getKeyboards(): void {
    this.keyboardService.getKeyboards()
      .subscribe(keyboards => this.keyboards = keyboards.slice(1, 5));
  }
}
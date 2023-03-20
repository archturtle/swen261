import { Component, OnInit } from '@angular/core';

import { Keyboard } from '../keyboard';
import { KeyboardService } from '../keyboard.service';

@Component({
  selector: 'app-keyboards',
  templateUrl: './keyboards.component.html',
  styleUrls: ['./keyboards.component.css']
})
export class KeyboardsComponent implements OnInit {
  keyboards: Keyboard[] = [];

  constructor(private keyboardService: KeyboardService) { }

  ngOnInit(): void {
    this.getKeyboards();
  }

  getKeyboards(): void {
    this.keyboardService.getKeyboards()
    .subscribe(keyboards => this.keyboards = keyboards);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.keyboardService.addKeyboard({ name } as Keyboard)
      .subscribe(keyboard => {
        this.keyboards.push(keyboard);
      });
  }

  delete(keyboard: Keyboard): void {
    this.keyboards = this.keyboards.filter(k => k !== keyboard);
    this.keyboardService.deleteKeyboard(keyboard.id).subscribe();
  }

}
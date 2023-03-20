import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Keyboard } from '../keyboard';
import { KeyboardService } from '../keyboard.service';

@Component({
  selector: 'app-keyboard-detail',
  templateUrl: './keyboard-detail.component.html',
  styleUrls: [ './keyboard-detail.component.css' ]
})

export class KeyboardDetailComponent implements OnInit {
  keyboard: Keyboard | undefined;

  constructor(
    private route: ActivatedRoute,
    private keyboardService: KeyboardService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getKeyboard();
  }

  getKeyboard(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.keyboardService.getKeyboard(id)
      .subscribe(keyboard => this.keyboard = keyboard);
  }

  goBack(): void {
    this.location.back();
  }
}
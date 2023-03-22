import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Keyboard } from 'src/app/interfaces/keyboard';
import { KeyboardService } from 'src/app/services/keyboard.service';

@Component({
  selector: 'app-keyboard-detail',
  templateUrl: './keyboard-detail.component.html',
  styleUrls: ['./keyboard-detail.component.css']
})
export class KeyboardDetailComponent implements OnInit {
  keyboard: Keyboard | null = null;
  selectedValue!: Event;
  quantity: number = 1;

  constructor(private route: ActivatedRoute, private keyboardService: KeyboardService) { }

  ngOnInit(): void {
    const id: string | null = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.keyboardService.getKeyboardById$(parseInt(id))
      .subscribe((data: Keyboard) => this.keyboard = data);
  }

  quantityChanged(value: number): void {
    this.quantity = value;
  }
}

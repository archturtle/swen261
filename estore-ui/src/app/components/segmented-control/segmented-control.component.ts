import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-segmented-control',
  templateUrl: './segmented-control.component.html',
  styleUrls: ['./segmented-control.component.css']
})
export class SegmentedControlComponent {
  @Output() controlChanged: EventEmitter<number> = new EventEmitter<number>();
  @Input() controlNames: string[] = [];
  selectedIndex: number = 0;

  setSelectedIndex(index: number) {
    this.selectedIndex = index;
    this.controlChanged.emit(index);
  }
}

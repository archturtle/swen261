import { AfterViewChecked, AfterViewInit, Component, ElementRef, HostListener, OnDestroy, ViewChild } from '@angular/core';
import { Subscription, debounceTime, fromEvent } from 'rxjs';

@Component({
  selector: 'app-keyboard-configurator',
  templateUrl: './keyboard-configurator.component.html',
  styleUrls: ['./keyboard-configurator.component.css']
})
export class KeyboardConfiguratorComponent implements AfterViewInit, OnDestroy {
  // Slider components
  @ViewChild("sliderThumb") sliderThumb!: ElementRef<HTMLSpanElement>;
  @ViewChild("sliderLayer") sliderLayer!: ElementRef<HTMLSpanElement>;

  // False means case, True means Keycaps
  thumbPosition: boolean = true;

  // Privates
  private resizeSubscription: Subscription;  

  constructor() {
    this.resizeSubscription = fromEvent(window, 'resize')
      .pipe(debounceTime(100))
      .subscribe(() => this.click());
  }

  ngAfterViewInit(): void { this.click(); }
  ngOnDestroy(): void { this.resizeSubscription.unsubscribe(); }

  click(): void {
    // let sliderThumbRect: DOMRect = this.sliderThumb.nativeElement.getBoundingClientRect()
    // let sliderLayerRect: DOMRect = this.sliderLayer.nativeElement.getBoundingClientRect();
    
    // if (this.thumbPosition) {
    //   this.sliderThumb.nativeElement.style.top = (sliderLayerRect.top).toString() + "px";
    // } else {
    //   this.sliderThumb.nativeElement.style.top = (sliderLayerRect.bottom - (sliderThumbRect.height)).toString() + "px";
    // }
  }
}

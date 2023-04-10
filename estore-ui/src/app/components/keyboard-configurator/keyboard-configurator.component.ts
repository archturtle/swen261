import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CustomKeyboardSwitchType, PricePerSwitchType } from 'src/app/interfaces/custom-keyboard';
import { EightyLayout } from 'src/app/layouts/eighty';
import { KeyCapOrientation, KeyCap, Key, Spacer } from 'src/app/layouts/key-cap';
import { KeyboardLayout } from 'src/app/layouts/keyboard-layout';
import { OneHundredLayout } from 'src/app/layouts/one_hundred';
import { SixtyLayout } from 'src/app/layouts/sixty';

@Component({
  selector: 'app-keyboard-configurator',
  templateUrl: './keyboard-configurator.component.html',
  styleUrls: ['./keyboard-configurator.component.css']
})
export class KeyboardConfiguratorComponent implements OnInit {
  // Form for color
  customKeyboardForm: FormGroup = this.formBuilder.group({
    boardColor: ['#8A2BE2'],
    keyCapColor: ['#f2f2f2'],
    labelColor: ['#000000'],
    selectedSwitch: ['CHERRY_MX_BLACK']
  });

  // Vars used in template
  math = Math;
  thumbPosition: boolean = true;
  keyCapOrientation = KeyCapOrientation;
  switchTypes = Object.keys(CustomKeyboardSwitchType).sort(); 
  selectedLayout: KeyboardLayout = OneHundredLayout;
  keyCapHSLColor: [number, number, number] = this.convertHextoHSL("#f2f2f2");

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.customKeyboardForm.valueChanges.subscribe((data) => {
      this.keyCapHSLColor = this.convertHextoHSL(data.keyCapColor);
    });
  }

  get boardColor() { return this.customKeyboardForm.get('boardColor'); }
  get keyCapColor() { return this.customKeyboardForm.get('keyCapColor'); }
  get labelColor() { return this.customKeyboardForm.get('labelColor'); }
  get switchType() { return this.customKeyboardForm.get('selectedSwitch') }

  controlSelected(index: number) {
    let layout: KeyboardLayout;
    switch (index) {
      case 0:
        layout = OneHundredLayout;
        break;
      case 1:
        layout = EightyLayout;
        break;
      case 2:
        layout = SixtyLayout;
        break;
      default:
        layout = OneHundredLayout;
        break
    }

    if (this.selectedLayout !== layout) this.selectedLayout = layout;
  }

  // Helpers for keyboard
  isKey(item: Key): item is KeyCap {
    return !Object.keys(item).includes("width") && !Object.keys(item).includes("height");
  }

  asKeyCap(item: any): KeyCap {
    return item as KeyCap;
  }

  asSpacer(item: any): Spacer {
    return item as Spacer;
  }

  keyClicked(event: any) { console.log(event) }

  caseClicked() { }

  getSwitchPrice(name: string): number {
    return this.selectedLayout.totalKeys * (name.includes("GATERON") ? PricePerSwitchType.GATERON : PricePerSwitchType.CHERRY_MX);
  }

  getSwitchName(name: string): string {
    let formattedName = name.split('_')
      .map((value) => {
        let lower = value.toLowerCase();
        return lower.charAt(0).toUpperCase() + lower.slice(1);
      }).join(' ');
    
    return formattedName;
  }

  getTotalPrice(): string {
    let switchPrice = this.getSwitchPrice(this.switchType?.value ?? "");
    return (this.selectedLayout.price + switchPrice).toFixed(2);
  }

  // Helpers for keyboard colors
  convertHextoHSL(hex: string): [number, number, number] {
    return this.convertRGBtoHSL(...this.convertHextoRGB(hex));
  }

  toHSLString(hsl: [number, number, number], l_adjust: number = 0): string {
    return `hsl(${hsl[0]}, ${hsl[1]}%, ${hsl[2] - l_adjust}%)`;
  }

  private convertHextoRGB(hex: string): [number, number, number] {
    hex = hex.substring(1);
    let r = parseInt(hex.substring(0, 2), 16),
        g = parseInt(hex.substring(2, 4), 16),
        b = parseInt(hex.substring(4, 6), 16);

    return [r, g, b];
  }

  private convertRGBtoHSL(r: number, g: number, b: number): [number, number, number] {
    r /= 255;
    g /= 255;
    b /= 255;
    let c_max = Math.max(r, g, b),
        c_min = Math.min(r, g, b),
        delta = c_max - c_min;
    let h = 0,
        s = 0,
        l = 0;

    if (delta == 0) {
      h = 0;
    } else if (c_max == r) {
      h = (((g - b) / delta) % 6);
    } else if (c_max == g) {
      h = (((b - r) / delta) + 2);
    } else {
      h = (((r - g) / delta) + 4);
    }
    h = Math.round(h * 60); 
    h += (h < 0) ? 360 : 0;

    l = (c_max + c_min) / 2;
    s = (delta == 0) ? 0 : delta / (1 - Math.abs(2 * l - 1));

    return [ h, s * 100, l * 100 ]
  }
}

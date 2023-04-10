import { CustomKeyboardSize } from "../interfaces/custom-keyboard";
import { KeyCapSize, KeyCapOrientation, KEY_CAP_SIZE } from "./key-cap";
import { KeyboardLayout } from "./keyboard-layout";

export const EightyLayout: KeyboardLayout = {
  functionKeys: [
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Esc' },
    { width: KEY_CAP_SIZE, height: KEY_CAP_SIZE }, // Spacer
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F1' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F2' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F3' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F4' },
    { width: KEY_CAP_SIZE / 2.0, height: KEY_CAP_SIZE }, // Spacer
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F5' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F6' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F7' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F8' },
    { width: KEY_CAP_SIZE / 2.0, height: KEY_CAP_SIZE }, // Spacer
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F9' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F10' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F11' },
    { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F12' },
  ],
  editingKeys: [
    [
      { width: KEY_CAP_SIZE / 4.0, height: KEY_CAP_SIZE },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'PrtSc' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'ScrLk' }, 
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Pause', bottomLabel: 'Break' },
    ],
    [
      { width: KEY_CAP_SIZE / 4.0, height: (KEY_CAP_SIZE / 2.0) - 5.0 },
      { width: KEY_CAP_SIZE, height: (KEY_CAP_SIZE / 2.0) - 5.0 },
      { width: KEY_CAP_SIZE, height: (KEY_CAP_SIZE / 2.0) - 5.0 },
      { width: KEY_CAP_SIZE, height: (KEY_CAP_SIZE / 2.0) - 5.0 },
    ],
    [
      { width: KEY_CAP_SIZE / 4.0, height: KEY_CAP_SIZE },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Insert' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Home' }, 
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'PgUp' },
    ],
    [
      { width: KEY_CAP_SIZE / 4.0, height: KEY_CAP_SIZE },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Del' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'End' }, 
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'PgDn' },
    ],
    [
      { width: KEY_CAP_SIZE / 4.0, height: KEY_CAP_SIZE },
      { width: KEY_CAP_SIZE, height: KEY_CAP_SIZE },
      { width: KEY_CAP_SIZE, height: KEY_CAP_SIZE },
      { width: KEY_CAP_SIZE, height: KEY_CAP_SIZE },
    ]
  ],
  arrowKeys: [
    [
      { width: KEY_CAP_SIZE / 4.0, height: KEY_CAP_SIZE },
      { width: KEY_CAP_SIZE, height: KEY_CAP_SIZE },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '↑' }, 
      { width: KEY_CAP_SIZE, height: KEY_CAP_SIZE },
    ],
    [
      { width: KEY_CAP_SIZE / 4.0, height: KEY_CAP_SIZE },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '←' }, 
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '↓' }, 
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '→' }, 
    ]
  ],
  typingKeys: [
    [
      { width: KEY_CAP_SIZE, height: (KEY_CAP_SIZE / 2.0) - 5 }
    ],
    [ 
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '~', bottomLabel: '`' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '!', bottomLabel: '1' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '@', bottomLabel: '2' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '#', bottomLabel: '3' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '$', bottomLabel: '4' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '%', bottomLabel: '5' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '^', bottomLabel: '6' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '&', bottomLabel: '7' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '*', bottomLabel: '8' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '(', bottomLabel: '9' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: ')', bottomLabel: '0' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '_', bottomLabel: '-' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '+', bottomLabel: '=' },
      { size: KeyCapSize.TWO_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Backspace' },
    ],
    [ 
      { size: KeyCapSize.ONE_POINT_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Tab' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Q' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'W' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'E' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'R' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'T' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Y' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'U' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'I' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'O' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'P' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '{', bottomLabel: '[' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '}', bottomLabel: ']' },
      { size: KeyCapSize.ONE_POINT_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '|', bottomLabel: '\\' },
    ],
    [ 
      { size: KeyCapSize.ONE_POINT_SEVEN_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Caps Lock' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'A' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'S' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'D' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'F' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'G' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'H' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'J' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'K' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'L' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: ':', bottomLabel: ';' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '"', bottomLabel: '\'' },
      { size: KeyCapSize.TWO_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Enter' },
    ],
    [ 
      { size: KeyCapSize.TWO_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Shift' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Z' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'X' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'C' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'V' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'B' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'N' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'M' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '<', bottomLabel: ',' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '>', bottomLabel: '.' },
      { size: KeyCapSize.ONE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: '?', bottomLabel: '/' },
      { size: KeyCapSize.TWO_POINT_SEVEN_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Shift' },
    ],
    [ 
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Ctrl' },
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Win' },
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Alt' },
      { size: KeyCapSize.SIX_POINT_TWO_FIZE_U, orientation: KeyCapOrientation.HORIZONTAL }, 
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Alt' },
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Win' },
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Menu' },
      { size: KeyCapSize.ONE_POINT_TWO_FIVE_U, orientation: KeyCapOrientation.HORIZONTAL, topLabel: 'Ctrl' },
    ],
  ],
  totalKeys: 87,
  price: 130.00,
  size: CustomKeyboardSize.EIGHTY
};
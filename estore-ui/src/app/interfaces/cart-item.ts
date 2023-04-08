import { CustomKeyboard } from "./custom-keyboard";

export enum CartItemType {
  STANDARD_KEYBOARD = "STANDARD_KEYBOARD",
  CUSTOM_KEYBOARD = "CUSTOM_KEYBOARD"
}

export interface CartItem {
  cartItemType: CartItemType;
  quantity: number;
  keyboardID?: number;
  customKeyboard?: CustomKeyboard;
}

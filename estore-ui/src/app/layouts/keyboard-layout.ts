import { CustomKeyboardSize } from "../interfaces/custom-keyboard";
import { Key } from "./key-cap";

export interface KeyboardLayout {
  functionKeys?: Key[];
  editingKeys?: Key[][];
  arrowKeys?: Key[][];
  numberPad?: Key[][];
  numberPadEndColumn?: Key[];
  typingKeys: Key[][];
  totalKeys: number;
  price: number;
  size: CustomKeyboardSize;
}
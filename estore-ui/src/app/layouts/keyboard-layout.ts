import { Key, KeyCap } from "./key-cap";

export interface KeyboardLayout {
  functionKeys?: KeyCap[];
  editingKeys?: KeyCap[][];
  arrowKeys?: KeyCap[][];
  numberPad?: KeyCap[][];
  typingKeys: KeyCap[][];
}
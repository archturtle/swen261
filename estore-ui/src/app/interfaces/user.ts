import { Keyboard } from "./keyboard";

export interface User {
  id?: number;
  name: string;
  role: number;
  cart: Keyboard[];
}

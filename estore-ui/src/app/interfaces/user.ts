import { CartItem } from "./cart-item";

export interface User {
  id: number;
  name: string;
  role: number;
  cart: CartItem[];
}

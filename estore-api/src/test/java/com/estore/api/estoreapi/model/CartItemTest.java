package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.CartItem.Type;
import com.estore.api.estoreapi.model.CustomKeyboard.Size;

@Tag("Model-tier")
public class CartItemTest {
  private CartItem.Type expectedCartItemType = Type.STANDARD_KEYBOARD;
  private int expectedCartItemQuantity = 100;
  private int expectedCartKeyboardID = 0;
  private CustomKeyboard expectedCustomKeyboard = null;
  private String expectedToString = "CartItem [cartItemType=STANDARD_KEYBOARD, quantity=100, keyboardID=0, customKeyboard=null]";
  private CartItem cartItem = new CartItem(this.expectedCartItemType, this.expectedCartItemQuantity, this.expectedCartKeyboardID, this.expectedCustomKeyboard);;

  @Test
  void testGetCartItemType() {
    assertEquals(this.expectedCartItemType, cartItem.getCartItemType());
  }

  @Test
  void testGetCustomKeyboard() {
    assertEquals(this.expectedCustomKeyboard, cartItem.getCustomKeyboard());
  }

  @Test
  void testGetKeyboardID() {
    assertEquals(this.expectedCartKeyboardID, cartItem.getKeyboardID());
  }

  @Test
  void testGetQuantity() {
    assertEquals(this.expectedCartItemQuantity, cartItem.getQuantity());
  }

  @Test
  void testSetCartItemType() {
    CartItem.Type newType = Type.CUSTOM_KEYBOARD;
    this.cartItem.setCartItemType(newType);
    assertEquals(newType, cartItem.getCartItemType());
  }

  @Test
  void testSetCustomKeyboard() {
    CustomKeyboard newKeyboard = new CustomKeyboard(Size.ONE_HUNDRED, 119.99, "#ff0000", "#00ff00", "#0000ff");
    this.cartItem.setCustomKeyboard(newKeyboard);
    assertEquals(newKeyboard, cartItem.getCustomKeyboard());
  }

  @Test
  void testSetKeyboardID() {
    int newID = -1;
    this.cartItem.setKeyboardID(newID);
    assertEquals(newID, cartItem.getKeyboardID());
  }

  @Test
  void testSetQuantity() {
    int newQuantity = 1;
    this.cartItem.setQuantity(newQuantity);
    assertEquals(newQuantity, cartItem.getQuantity());
  }

  @Test
  void testToString() {
    assertEquals(expectedToString, this.cartItem.toString());
  }
}

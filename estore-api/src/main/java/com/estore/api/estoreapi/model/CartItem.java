package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItem {
  private static final String STRING_FORMAT = "CartItem [cartItemType=%s, quantity=%d, keyboardID=%d, customKeyboard=%s]";

  // The type enum
  enum Type {
    STANDARD_KEYBOARD,
    CUSTOM_KEYBOARD
  }

  // Generic data
  @JsonProperty("cartItemType")
  private Type cartItemType;

  @JsonProperty("quantity")
  private int quantity;

  // Data if CartItemType = STANDARD_KEYBOARD
  @JsonProperty("keyboardID")
  private int keyboardID;

  // Data if CartItemType = CUSTOM_KEYBOARD
  @JsonProperty("customKeyboard")
  private CustomKeyboard customKeyboard;

  public CartItem(
    @JsonProperty("cartItemType") Type cartItemType, 
    @JsonProperty("quantity") int quantity, 
    @JsonProperty("keyboardID") int keyboardID, 
    @JsonProperty("customKeyboard") CustomKeyboard customKeyboard) {
    this.cartItemType = cartItemType;
    this.quantity = quantity;
    this.keyboardID = keyboardID;
    this.customKeyboard = customKeyboard;
  }

  public Type getCartItemType() {
    return cartItemType;
  }

  public void setCartItemType(Type cartItemType) {
    this.cartItemType = cartItemType;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getKeyboardID() {
    return keyboardID;
  }

  public void setKeyboardID(int keyboardID) {
    this.keyboardID = keyboardID;
  }

  public CustomKeyboard getCustomKeyboard() {
    return customKeyboard;
  }

  public void setCustomKeyboard(CustomKeyboard customKeyboard) {
    this.customKeyboard = customKeyboard;
  }

  @Override
  public String toString() {
    return String.format(STRING_FORMAT, cartItemType, quantity, keyboardID, customKeyboard);
  }
}

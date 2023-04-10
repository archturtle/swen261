package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a CartItem entity.
 */
public class CartItem {
  /** 
   * The format to use when toString() is called.
   */
  private static final String STRING_FORMAT = "CartItem [cartItemType=%s, quantity=%d, keyboardID=%d, customKeyboard=%s]";

  /**
   * The different types of cart item.
   */
  public enum Type {
    STANDARD_KEYBOARD,
    CUSTOM_KEYBOARD
  }

  /**
   * The type that the cart item represents
   */
  @JsonProperty("cartItemType")
  private Type cartItemType;

  /**
   * The quantity of the cart item.
   */
  @JsonProperty("quantity")
  private int quantity;

  /**
   * The keyboard id that the cart item represents (null if type is custom keyboard.)
   */
  @JsonProperty("keyboardID")
  private int keyboardID;

  /**
   * The custom keyboard that the cart item represents (null if type is standard keyboard.)
   */
  @JsonProperty("customKeyboard")
  private CustomKeyboard customKeyboard;

  /**
   * Creates a CartItem object with the passed parameters.
   * 
   * @param cartItemType The type of the cart item.
   * @param quantity The quantity of the cart item.
   * @param keyboardID The keyboard id of the cart item.
   * @param customKeyboard The custom keyboard of the cart item.
   */
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

  /**
   * Gets the type of the cart item.
   * 
   * @return The type of the cart item.
   */
  public Type getCartItemType() {
    return cartItemType;
  }

  /**
   * Sets the type of the cart item.
   * 
   * @param cartItemType The new type of the cart item.
   */
  public void setCartItemType(Type cartItemType) {
    this.cartItemType = cartItemType;
  }

  /**
   * Gets the quantity of the cart item.
   * 
   * @return The quantity of the cart item.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the cart item.
   * 
   * @param quantity The new quantity of the cart item.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the keyboard id of the cart item.
   * 
   * @return The keyboard id of the cart item.
   */
  public int getKeyboardID() {
    return keyboardID;
  }

  /**
   * Sets the keyboard id of the cart item.
   * 
   * @param keyboardID The new keyboard ID of the cart item.
   */
  public void setKeyboardID(int keyboardID) {
    this.keyboardID = keyboardID;
  }

  /**
   * Gets the custom keyboard of the cart item.
   * 
   * @return The custom keyboard of the cart item.
   */
  public CustomKeyboard getCustomKeyboard() {
    return customKeyboard;
  }

  /**
   * Sets the custom keyboard of the cart item.
   * 
   * @param customKeyboard The new custom keyboard of the cart item.
   */
  public void setCustomKeyboard(CustomKeyboard customKeyboard) {
    this.customKeyboard = customKeyboard;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(STRING_FORMAT, cartItemType, quantity, keyboardID, customKeyboard);
  }
}

package com.estore.api.estoreapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a User entity.
 */
public class User {
  /** The format to use when toString() is called. */
  private static final String STRING_FORMAT = "User [id=%d, name=%s, role=%d, cart=%s]";

  /** The id associated with the user. */
  @JsonProperty("id")
  private int id;

  /** The name of the user */
  @JsonProperty("name")
  private String name;

  /** The role of the user. */
  @JsonProperty("role")
  private int role;

  /** The cart of the user. */
  @JsonProperty("cart")
  private List<CartItem> cart;

  /**
   * Creates a new User object with the given parameters.
   * 
   * @param id The id of the user.
   * @param name The name of the user.
   * @param role The role of the user.
   * @param cart The cart of the user.
   */
  public User(@JsonProperty("id") int id, 
              @JsonProperty("name") String name,
              @JsonProperty("role") int role,
              @JsonProperty("cart") List<CartItem> cart) {
    this.id = id;
    this.name = name;
    this.role = role;
    this.cart = cart;
  }

  /**
   * Gets the id of the user.
   * 
   * @return The user id.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the name of the user.
   * 
   * @param name The new name of the user.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the name of the user.
   * 
   * @return The name of the user.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the role of the user.
   * 
   * @param role The new role of the user.
   */
  public void setRole(int role) {
    this.role = role;
  }

  /**
   * Gets the role of the user.
   * 
   * @return The role of the user.
   */
  public int getRole() {
    return this.role;
  }

  /**
   * Gets the cart of the user.
   * 
   * @return The cart of the user.
   */
  public List<CartItem> getCart() {
    return this.cart;
  }

  /**
   * Sets the cart of the user.
   * 
   * @param cart The new cart of the user.
   */
  public void setCart(List<CartItem> cart) {
    this.cart = cart;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("[\n");
    this.cart.forEach(keyboard -> stringBuilder.append("\t" + keyboard.toString() + "\n"));
    stringBuilder.append("]");

    return String.format(STRING_FORMAT, this.id, this.name, this.role, stringBuilder.toString());
  }
}

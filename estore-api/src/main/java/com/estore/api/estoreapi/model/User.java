package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
  private static final String STRING_FORMAT = "User [id=%d, name=%s, role=%d, cart=%s]";

  @JsonProperty("id")
  private int id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("role")
  private int role;

  @JsonProperty("cart")
  private List<Integer> cart;

  public User(@JsonProperty("id") int id, 
              @JsonProperty("name") String name,
              @JsonProperty("role") int role) {
    this.id = id;
    this.name = name;
    this.role = role;
    this.cart = new ArrayList<>();
  }

  public int getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setRole(int role) {
    this.role = role;
  }

  public int getRole() {
    return this.role;
  }

  public List<Integer> getCart() {
    return this.cart;
  }

  public boolean addToCart(Integer item) {
    return this.cart.add(item);
  }

  public boolean removeFromCart(Integer item) {
    return this.cart.remove(item);
  }

  public void clearCart() {
    this.cart.clear();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("[\n");
    this.cart.forEach(keyboard -> stringBuilder.append("\t" + keyboard.toString() + "\n"));
    stringBuilder.append("]");

    return String.format(STRING_FORMAT, this.id, this.name, this.role, stringBuilder.toString());
  }
}

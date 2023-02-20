package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Keyboard entity
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
public class Keyboard {
  // Package private for tests
  private static final String STRING_FORMAT = "Keyboard [id=%d, name=%s, price=%f, quantity=%d]";

  @JsonProperty("id") private int id;
  @JsonProperty("name") private String name;
  @JsonProperty("price") private double price;
  @JsonProperty("quantity") private int quantity;

  /**
   * Create a keyboard with the given id, name, price and quantity
   * @param id The id of the keyboard
   * @param name The name of the keyboard
   * @param price The price of the keyboard
   * @param quantity The quantity of the keyboard
   *
   * {@literal @}JsonProperty is used in serialization and deserialization
   * of the JSON object to the Java object in mapping the fields.  If a field
   * is not provided in the JSON object, the Java field gets the default Java
   * value, i.e. 0 for int
   */
  public Keyboard(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("quantity") int quantity) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.quantity = quantity;
  }

  /**
   * Retrieves the id of the keyboard
   * @return The id of the keyboard
   */
  public int getId() { return this.id; }

  /**
   * Sets the name of the keyboard - necessary for JSON object to Java object deserialization
   * @param name The name of the keyboard
   */
  public void setName(String name) { this.name = name; }

  /**
   * Retrieves the name of the keyboard
   * @return The name of the keyboard
   */
  public String getName() { return this.name; }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
      return String.format(STRING_FORMAT, this.id, this.name, this.price, this.quantity);
  }
}
package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Keyboard entity.
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
public class Keyboard {
  /**
   * The format that should be used when printing a {@linkplain Keyboard keyboard}
   * object.
   */
  private static final String STRING_FORMAT = "Keyboard [id=%d, name=%s, price=%f, quantity=%d]";

  /**
   * The ID associated with this {@linkplain Keyboard keyboard}.
   */
  @JsonProperty("id")
  private int id;

  /**
   * The name associated with this {@linkplain Keyboard keyboard}.
   */
  @JsonProperty("name")
  private String name;

  /**
   * The price associated with this {@linkplain Keyboard keyboard}.
   */
  @JsonProperty("price")
  private double price;

  /**
   * The quantity associated with this {@linkplain Keyboard keyboard}.
   */
  @JsonProperty("quantity")
  private int quantity;

  /**
   * Create a keyboard with the given id, name, price and quantity
   * 
   * @param id       The id of the keyboard
   * @param name     The name of the keyboard
   * @param price    The price of the keyboard
   * @param quantity The quantity of the keyboard
   *
   *                 {@literal @}JsonProperty is used in serialization and
   *                 deserialization
   *                 of the JSON object to the Java object in mapping the fields.
   *                 If a field
   *                 is not provided in the JSON object, the Java field gets the
   *                 default Java
   *                 value, i.e. 0 for int
   */
  public Keyboard(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("price") double price,
      @JsonProperty("quantity") int quantity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * Retrieves the id of the keyboard
   * 
   * @return The id of the keyboard
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the name of the keyboard - necessary for JSON object to Java object
   * deserialization
   * 
   * @param name The name of the keyboard
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the name of the keyboard
   * 
   * @return The name of the keyboard
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the price of the keyboard - necessary for JSON object to Java object
   * deserialization
   * 
   * @param name The price of the keyboard
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Retrieves the price of the keyboard
   * 
   * @return The price of the keyboard
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Sets the quantity of the keyboard - necessary for JSON object to Java object
   * deserialization
   * 
   * @param name The quantity of the keyboard
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Retrieves the quantity of the keyboard
   * 
   * @return The quantity of the keyboard
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(STRING_FORMAT, this.id, this.name, this.price, this.quantity);
  }
}
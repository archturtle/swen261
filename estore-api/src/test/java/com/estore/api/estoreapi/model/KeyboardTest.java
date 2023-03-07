package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The unit test class for the Keyboard class.
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
public class KeyboardTest {
  /* The expected ID for the keyboard object. */
  private final int expectedID = 1;
  /* The expected name for the keyboard object. */
  private final String expectedName = "GMMK PRO";
  /* The expected price for the keyboard object. */
  private final double expectedPrice = 349.99;
  /* The expected quantity for the keyboard object. */
  private final int expectedQuantity = 300;
  /* The expected string for the keyboard object. */
  private final String expectedToString = "Keyboard [id=1, name=GMMK PRO, price=349.990000, quantity=300]";

  /**
   * Tests whether the Keyboard object is constructed properly.
   */
  @Test
  public void testConstruction() {
    Keyboard newKeyboard = new Keyboard(expectedID, expectedName, expectedPrice, expectedQuantity);

    assertEquals(newKeyboard.getId(), expectedID);
    assertEquals(newKeyboard.getName(), expectedName);
    assertEquals(newKeyboard.getPrice(), expectedPrice);
    assertEquals(newKeyboard.getQuantity(), expectedQuantity);
  }

  /**
   * Tests whether the keyboard name is set properly.
   */
  @Test
  public void testSetName() {
    Keyboard newKeyboard = new Keyboard(expectedID, expectedName, expectedPrice, expectedQuantity);

    String newName = "GMMK 2";
    newKeyboard.setName(newName);
    assertEquals(newKeyboard.getName(), newName);
  }

  /**
   * Tests whether the keyboard price is set properly.
   */
  @Test
  public void testSetPrice() {
    Keyboard newKeyboard = new Keyboard(expectedID, expectedName, expectedPrice, expectedQuantity);

    double newPrice = 300.00;
    newKeyboard.setPrice(newPrice);
    assertEquals(newKeyboard.getPrice(), newPrice);
  }

  /**
   * Tests whether the keyboard quantity is set properly.
   */
  @Test
  public void testQuantity() {
    Keyboard newKeyboard = new Keyboard(expectedID, expectedName, expectedPrice, expectedQuantity);

    int newQuantity = 150;
    newKeyboard.setQuantity(newQuantity);
    assertEquals(newKeyboard.getQuantity(), newQuantity);

  }

  /**
   * Tests whether the keyboard to string is displaying properly.
   */
  @Test
  public void testToString() {
    Keyboard newKeyboard = new Keyboard(expectedID, expectedName, expectedPrice, expectedQuantity);

    assertEquals(newKeyboard.toString(), expectedToString);
  }
}

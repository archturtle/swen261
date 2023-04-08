package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.CustomKeyboard.Size;

@Tag("Model-tier")
public class CustomKeyboardTest {
  private CustomKeyboard.Size expectedCustomKeyboardSize = Size.ONE_HUNDRED;
  private double expectedCustomKeyboardPrice = 399.99;
  private String expectedKeyboardCaseColor = "#FF0000";
  private String expectedKeyboardKeyCapColor = "#00FF00";
  private String expectedKeyboardSwitchColor = "#0000FF";
  private String expectedToString = "CustomKeyboard [size=ONE_HUNDRED, price=399.990000, caseColor=#FF0000, keycapColor=#00FF00, switchColor=#0000FF]";
  private CustomKeyboard customKeyboard = new CustomKeyboard(expectedCustomKeyboardSize, expectedCustomKeyboardPrice, expectedKeyboardCaseColor, expectedKeyboardKeyCapColor, expectedKeyboardSwitchColor);

  @Test
  void testGetCaseColor() {
    assertEquals(expectedKeyboardCaseColor, this.customKeyboard.getCaseColor());
  }

  @Test
  void testGetKeycapColor() {
    assertEquals(expectedKeyboardKeyCapColor, this.customKeyboard.getKeycapColor());
  }

  @Test
  void testGetPrice() {
    assertEquals(expectedCustomKeyboardPrice, this.customKeyboard.getPrice());
  }

  @Test
  void testGetSize() {
    assertEquals(expectedCustomKeyboardSize, this.customKeyboard.getSize());
  }

  @Test
  void testGetSwitchColor() {
    assertEquals(expectedKeyboardSwitchColor, this.customKeyboard.getSwitchColor());
  }

  @Test
  void testSetCaseColor() {
    String newColor = "#FFFF00";
    this.customKeyboard.setCaseColor(newColor);
    assertEquals(newColor, this.customKeyboard.getCaseColor());
  }

  @Test
  void testSetKeycapColor() {
    String newColor = "#FFFFFF";
    this.customKeyboard.setKeycapColor(newColor);
    assertEquals(newColor, this.customKeyboard.getKeycapColor());
  }

  @Test
  void testSetPrice() {
    double newPrice = 1229.99;
    this.customKeyboard.setPrice(newPrice);
    assertEquals(newPrice, this.customKeyboard.getPrice());
  }

  @Test
  void testSetSize() {
    CustomKeyboard.Size newSize = Size.SIXTY;
    this.customKeyboard.setSize(newSize);
    assertEquals(newSize, this.customKeyboard.getSize());
  }

  @Test
  void testSetSwitchColor() {
    String newColor = "#00F0F0";
    this.customKeyboard.setSwitchColor(newColor);
    assertEquals(newColor, this.customKeyboard.getSwitchColor());
  }

  @Test
  void testToString() {
    assertEquals(expectedToString, this.customKeyboard.toString());
  }
}

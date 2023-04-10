package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.CustomKeyboard.Size;
import com.estore.api.estoreapi.model.CustomKeyboard.SwitchType;

@Tag("Model-tier")
public class CustomKeyboardTest {
  private CustomKeyboard.Size expectedCustomKeyboardSize = Size.ONE_HUNDRED;
  private double expectedCustomKeyboardPrice = 399.99;
  private String expectedKeyboardCaseColor = "#FF0000";
  private String expectedKeyboardKeyCapColor = "#00FF00";
  private String expectedKeyboardLabelColor = "#000000";
  private CustomKeyboard.SwitchType expectedCustomKeyboardSwitchType = SwitchType.CHERRY_MX_BLACK;
  private CustomKeyboard customKeyboard = new CustomKeyboard(expectedCustomKeyboardSize, expectedCustomKeyboardPrice, expectedKeyboardCaseColor, expectedKeyboardKeyCapColor, expectedKeyboardLabelColor, expectedCustomKeyboardSwitchType);
  private String expectedToString = "CustomKeyboard [size=ONE_HUNDRED, price=399.990000, caseColor=#FF0000, keycapColor=#00FF00, labelColor=#000000, switchType=CHERRY_MX_BLACK]";

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
  void testGetSwitchType() {
    assertEquals(expectedCustomKeyboardSwitchType, this.customKeyboard.getSwitchType());
  }

  @Test
  void testGetLabelColor() {
    assertEquals(expectedKeyboardLabelColor, this.customKeyboard.getLabelColor());
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
  void testSetLabelColor() {
    String newColor = "#FFFFFF";
    this.customKeyboard.setLabelColor(newColor);
    assertEquals(newColor, this.customKeyboard.getLabelColor());
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
  void testSetSwitchType() {
    SwitchType newType = SwitchType.GATERON_CLEAR; 
    this.customKeyboard.setSwitchType(newType);
    assertEquals(newType, this.customKeyboard.getSwitchType());
  }

  @Test
  void testToString() {
    assertEquals(expectedToString, this.customKeyboard.toString());
  }
}

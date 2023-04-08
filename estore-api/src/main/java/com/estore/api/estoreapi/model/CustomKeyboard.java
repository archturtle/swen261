package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomKeyboard {
  private static final String STRING_FORMAT = "CustomKeyboard [size=%s, price=%f, caseColor=%s, keycapColor=%s, switchColor=%s]";

  public enum Size {
    ONE_HUNDRED,
    EIGHTY,
    SIXTY
  }
   
  public enum SwitchType {
    GATERON_BLACK,
    CHERRY_MX_BLACK,
    GATERON_BLUE,
    CHERRY_MX_BLUE,
    GATERON_BROWN,
    CHERRY_MX_BROWN,
    GATERON_CLEAR,
    CHERRY_MX_CLEAR,
    GATERON_GREEN,
    CHERRY_MX_GREEN,
    GATERON_RED,
    CHERRY_MX_RED
  }

  @JsonProperty("size")
  private Size size;

  @JsonProperty("price")
  private double price;

  @JsonProperty("caseColor")
  private String caseColor;

  @JsonProperty("keycapColor")
  private String keycapColor;

  @JsonProperty("switchColor")
  private SwitchType switchColor;

  public CustomKeyboard(
    @JsonProperty("size") Size size, 
    @JsonProperty("price") double price, 
    @JsonProperty("caseColor") String caseColor, 
    @JsonProperty("keycapColor") String keycapColor, 
    @JsonProperty("switchColor") SwitchType switchColor) {
    this.size = size;
    this.price = price;
    this.caseColor = caseColor;
    this.keycapColor = keycapColor;
    this.switchColor = switchColor;
  }

  public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getCaseColor() {
    return caseColor;
  }

  public void setCaseColor(String caseColor) {
    this.caseColor = caseColor;
  }

  public String getKeycapColor() {
    return keycapColor;
  }

  public void setKeycapColor(String keycapColor) {
    this.keycapColor = keycapColor;
  }

  public SwitchType getSwitchType() {
    return switchColor;
  }

  public void setSwitchType(SwitchType switchColor) {
    this.switchColor = switchColor;
  }

  @Override
  public String toString() {
    return String.format(STRING_FORMAT, size, price, caseColor, keycapColor, switchColor);
  }
}

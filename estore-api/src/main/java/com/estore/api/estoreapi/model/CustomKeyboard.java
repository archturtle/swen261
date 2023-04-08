package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomKeyboard {
  private static final String STRING_FORMAT = "CustomKeyboard [size=%s, price=%f, caseColor=%s, keycapColor=%s, switchColor=%s]";

  enum Size {
    ONE_HUNDRED,
    EIGHTY,
    SIXTY
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
  private String switchColor;

  public CustomKeyboard(
    @JsonProperty("size") Size size, 
    @JsonProperty("price") double price, 
    @JsonProperty("caseColor") String caseColor, 
    @JsonProperty("keycapColor") String keycapColor, 
    @JsonProperty("switchColor") String switchColor) {
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

  public String getSwitchColor() {
    return switchColor;
  }

  public void setSwitchColor(String switchColor) {
    this.switchColor = switchColor;
  }

  @Override
  public String toString() {
    return String.format(STRING_FORMAT, size, price, caseColor, keycapColor, switchColor);
  }
}

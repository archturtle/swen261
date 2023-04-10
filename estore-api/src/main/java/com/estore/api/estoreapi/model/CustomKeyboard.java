package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a CustomKeyboard entity.
 */
public class CustomKeyboard {
  /**
   * The format to use when toString() is called.
   */
  private static final String STRING_FORMAT = "CustomKeyboard [size=%s, price=%f, caseColor=%s, keycapColor=%s, labelColor=%s, switchType=%s]";

  /**
   * The different types of sizes for a CustomKeyboard.
   */
  public enum Size {
    ONE_HUNDRED,
    EIGHTY,
    SIXTY
  }

  /**
   * The different type of switches for a CustomKeyboard.
   */   
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

  /**
   * The size of the CustomKeyboard.
   */
  @JsonProperty("size")
  private Size size;

  /**
   * The price of the CustomKeyboard.
   */
  @JsonProperty("price")
  private double price;

  /**
   * The case color of the CustomKeyboard
   */
  @JsonProperty("caseColor")
  private String caseColor;

  /**
   * The keycap color of the CustomKeyboard.
   */
  @JsonProperty("keycapColor")
  private String keycapColor;

  /**
   * The label color of the CustomKeyboard.
   */
  @JsonProperty("labelColor")
  private String labelColor;

  /**
   * The switch type of the CustomKeyboard.
   */
  @JsonProperty("switchType")
  private SwitchType switchType;

  /**
   * Creates a new CustomKeyboard with the passed parameters.
   * 
   * @param size The size of the custom keyboard.
   * @param price The price of the custom keyboard.
   * @param caseColor The case color of the custom keyboard.
   * @param keycapColor The keycap color of the custom keyboard.
   * @param labelColor The label color of the custom keyboard.
   * @param switchType The switch type of the custom keyboard.
   */
  public CustomKeyboard(
    @JsonProperty("size") Size size, 
    @JsonProperty("price") double price, 
    @JsonProperty("caseColor") String caseColor, 
    @JsonProperty("keycapColor") String keycapColor, 
    @JsonProperty("labelColor") String labelColor,
    @JsonProperty("switchType") SwitchType switchType) {
    this.size = size;
    this.price = price;
    this.caseColor = caseColor;
    this.keycapColor = keycapColor;
    this.labelColor = labelColor;
    this.switchType = switchType;
  }

  /**
   * Gets the size of the custom keyboard.
   * 
   * @return The size of the custom keyboard.
   */
  public Size getSize() {
    return size;
  }

  /**
   * Sets the size of the custom keyboard.
   * 
   * @param size The new size of the custom keyboard.
   */
  public void setSize(Size size) {
    this.size = size;
  }

  /**
   * Gets the price of the custom keyboard.
   * 
   * @return The price of the custom keyboard.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of the custom keyboard.
   * 
   * @param price The new price of the custom keyboard.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Gets the case color of the custom keyboard.
   * 
   * @return The case color of the custom keyboard.
   */
  public String getCaseColor() {
    return caseColor;
  }

  /**
   * Sets the case color of the custom keyboard.
   * 
   * @param caseColor The new case color of the custom keyboard.
   */
  public void setCaseColor(String caseColor) {
    this.caseColor = caseColor;
  }

  /**
   * Gets the keycap color of the custom keyboard.
   * 
   * @return The keycap color of the custom keyboard.
   */
  public String getKeycapColor() {
    return keycapColor;
  }

  /**
   * Sets the keycap color of the custom keyboard.
   * 
   * @param keycapColor The new keycap color of the custom keyboard.
   */
  public void setKeycapColor(String keycapColor) {
    this.keycapColor = keycapColor;
  }

  /**
   * Gets the label color of the custom keyboard.
   * 
   * @return The label color of the custom keyboard.
   */
  public String getLabelColor() {
    return this.labelColor;
  }

  /**
   * Sets the label color of the custom keyboard.
   * 
   * @param labelColor The new label color of the custom keyboard.
   */
  public void setLabelColor(String labelColor) {
    this.labelColor = labelColor;
  }

  /**
   * Gets the switch type of the custom keyboard.
   * 
   * @return The switch type of the custom keyboard.
   */
  public SwitchType getSwitchType() {
    return switchType;
  }

  /**
   * Sets the switch type of the custom keyboard.
   * 
   * @param switchType The new switch type of the custom keyboard.
   */
  public void setSwitchType(SwitchType switchType) {
    this.switchType = switchType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(STRING_FORMAT, size, price, caseColor, keycapColor, labelColor, switchType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
      boolean result = false;

      if (obj instanceof CustomKeyboard) {
          CustomKeyboard other = (CustomKeyboard) obj;
          result = this.size.equals(other.size) &&
                   this.price == other.price &&
                   this.caseColor.equals(other.caseColor) &&
                   this.keycapColor.equals(other.keycapColor) &&
                   this.labelColor.equals(other.labelColor) &&
                   this.switchType.equals(other.switchType); 
      }

      return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Double.hashCode(this.price) * 
           this.size.hashCode() * this.caseColor.hashCode() * 
           this.labelColor.hashCode() * this.keycapColor.hashCode() * 
           this.switchType.hashCode();
  }
}

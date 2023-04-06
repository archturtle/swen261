package com.estore.api.estoreapi.model;

import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the data sent when checking out.
 */
public class CheckoutData {
   /**
   * The format that should be used when printing a {@linkplain CheckoutData checkoutData}
   * object.
   */
  private static final String STRING_FORMAT = "CheckoutData [userID=%d, creditCardNumber=%s, creditCardExpiration=%s, creditCardCVC=%d, creditCardHolder=%s, creditCardZipCode=%d]";
  
  /**
   * The date formatter used for toString();
   */
  private static final Format DATE_FORMAT = new SimpleDateFormat("MM/YY");

  /**
   * The user associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("userID")
  private int userID;

  /**
   * The credit card number associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("ccNumber")
  private String creditCardNumber;

  /**
   * The credit card expiration date associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("ccExpiration")
  @DateTimeFormat(pattern="MM/yy")
  private Date creditCardExpiration;

  /**
   * The credit card cvc associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("ccCVC")
  private int creditCardCVC;

  /**
   * The credit card holder associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("ccHolder")
  private String creditCardHolder;

  /**
   * The credit card zip code associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("ccZipCode")
  private int creditCardZipCode;


  /**
   * Create a CheckoutData object with the given userID, creditCardNumber, creditCardExpiration, 
   * creditCardCVC, creditCardHolder and creditCardZipCode.
   * 
   * @param userId                  The userID associated with the checkout data. 
   * @param creditCardNumber        The number of the credit card
   * @param creditCardExpiration    The expiration of the credit card
   * @param creditCardCVC           The cvc of the credit card
   * @param creditCardHolder        The holder of the credit card
   * @param creditCardZipCode       The zip code of the credit card.
   */
  public CheckoutData(@JsonProperty("userID") int userID, @JsonProperty("ccNumber") String creditCardNumber, @JsonProperty("ccExpiration") @DateTimeFormat(pattern="MM/yy") Date creditCardExpiration, 
  @JsonProperty("ccCVC") int creditCardCVC, @JsonProperty("ccHolder") String creditCardHolder, @JsonProperty("ccZipCode") int creditCardZipCode) {
    this.userID = userID;
    this.creditCardNumber = creditCardNumber;
    this.creditCardExpiration = creditCardExpiration;
    this.creditCardCVC = creditCardCVC;
    this.creditCardHolder = creditCardHolder;
    this.creditCardZipCode = creditCardZipCode;
  }

  /**
   * Sets the user id of the checkout data.
   * 
   * @param id The user id of the checkout data.
   */
  public void setUserID(int id) {
    this.userID = id;
  }

  /**
   * Retrieves the user id of the checkout data.
   * 
   * @return The user id of the checkout data. 
   */
  public int getUserID() {
    return this.userID;
  }

  /**
   * Sets the credit card number of the checkout data.
   * 
   * @param number The credit card number of the checkout data.
   */
  public void setCreditCardNumber(String number) {
    this.creditCardNumber = number;
  } 

  /**
   * Gets the credit card number of the checkout data.
   * 
   * @return The credit card number of the checkout data.
   */
  public String getCreditCardNumber() {
    return this.creditCardNumber;
  }

  /**
   * Sets the credit card expiration of the checkout data.
   * 
   * @param expiration The credit card expiration of the checkout data.
   */
  public void setCreditCardExpiration(Date expiration) {
    this.creditCardExpiration = expiration;
  } 

  /**
   * Gets the credit card expiration of the checkout data.
   * 
   * @return The credit card expiration of the checkout data.
   */
  public Date getCreditCardExpiration() {
    return this.creditCardExpiration;
  }

  /**
   * Sets the credit card cvc of the checkout data.
   * 
   * @param cvc The credit card cvc of the checkout data.
   */
  public void setCreditCardCVC(int cvc) {
    this.creditCardCVC = cvc;
  } 

  /**
   * Gets the credit card cvc of the checkout data.
   * 
   * @return The credit card cvc of the checkout data.
   */
  public int getCreditCardCVC() {
    return this.creditCardCVC;
  }

  /**
   * Sets the credit card holder of the checkout data.
   * 
   * @param holder The credit card holder of the checkout data.
   */
  public void setCreditCardHolder(String holder) {
    this.creditCardHolder = holder;
  } 

  /**
   * Gets the credit card holder of the checkout data.
   * 
   * @return The credit card holder of the checkout data.
   */
  public String getCreditCardHolder() {
    return this.creditCardHolder;
  }

  /**
   * Sets the credit card zip code of the checkout data.
   * 
   * @param zipCode The credit card zip code of the checkout data.
   */
  public void setCreditCardZipCode(int zipCode) {
    this.creditCardZipCode = zipCode;
  } 

  /**
   * Gets the credit card zip code of the checkout data.
   * 
   * @return The credit card zip code of the checkout data.
   */
  public int getCreditCardZipCode() {
    return this.creditCardZipCode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(STRING_FORMAT, this.userID, this.creditCardNumber, DATE_FORMAT.format(this.creditCardExpiration), this.creditCardCVC, this.creditCardHolder, this.creditCardZipCode);
  } 
}

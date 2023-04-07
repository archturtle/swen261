package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the data sent when checking out.
 */
public class CheckoutData {
   /**
   * The format that should be used when printing a {@linkplain CheckoutData checkoutData}
   * object.
   */
  private static final String STRING_FORMAT = "CheckoutData [userID=%d, firstName=%s, lastName=%s, address=%s, city=%s, state=%s, country=%s, zipCode=%d, email=%s, phoneNumber=%s, creditCardNumber=%s, creditCardExpiration=%s, creditCardCVC=%d, creditCardHolder=%s, creditCardZipCode=%d]";
  
  /**
   * The user associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("userID")
  private int userID;

  /**
   * The first name associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("firstName")
  private String firstName;

  /**
   * The last name associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("lastName")
  private String lastName;

  /**
   * The address associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("address")
  private String address;

  /**
   * The city associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("city")
  private String city;

  /**
   * The state associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("state")
  private String state;

  /**
   * The country associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("country")
  private String country;

  /**
   * The zip code associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("zipCode")
  private int zipCode;

  /**
   * The email associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("email")
  private String email;

  /**
   * The phone number associated with this {@linkplain CheckoutData checkoutData}. 
   */
  @JsonProperty("phoneNumber")
  private String phoneNumber;

  /**
   * The credit card number associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("creditCardNumber")
  private String creditCardNumber;

  /**
   * The credit card expiration date associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("creditCardExpiration")
  private String creditCardExpiration;

  /**
   * The credit card cvc associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("creditCardCVC")
  private int creditCardCVC;

  /**
   * The credit card holder associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("creditCardHolder")
  private String creditCardHolder;

  /**
   * The credit card zip code associated with this {@linkplain CheckoutData checkoutData}.
   */
  @JsonProperty("creditCardZipCode")
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
  public CheckoutData(
    @JsonProperty("userID") int userID,
    @JsonProperty("firstName") String firstName, 
    @JsonProperty("lastName") String lastName, 
    @JsonProperty("address") String address, 
    @JsonProperty("city") String city, 
    @JsonProperty("state") String state, 
    @JsonProperty("country") String country, 
    @JsonProperty("zipCode") int zipCode, 
    @JsonProperty("email") String email, 
    @JsonProperty("phoneNumber") String phoneNumber, 
    @JsonProperty("creditCardNumber") String creditCardNumber, 
    @JsonProperty("creditCardExpiration") String creditCardExpiration, 
    @JsonProperty("creditCardCVC") int creditCardCVC, 
    @JsonProperty("creditCardHolder") String creditCardHolder, 
    @JsonProperty("creditCardZipCode") int creditCardZipCode) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.state = state;
    this.country = country;
    this.zipCode = zipCode;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.creditCardNumber = creditCardNumber;
    this.creditCardExpiration = creditCardExpiration;
    this.creditCardCVC = creditCardCVC;
    this.creditCardHolder = creditCardHolder;
    this.creditCardZipCode = creditCardZipCode;
  }

  /**
   * Sets the first name of the checkout data.
   * 
   * @param firstName The first name of the checkout data.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Retrieves the first name of the checkout data.
   * 
   * @return The first name of the checkout data. 
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the last name of the checkout data.
   * 
   * @param lastName The last name of the checkout data.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Retrieves the last name of the checkout data.
   * 
   * @return The last name of the checkout data. 
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the address of the checkout data.
   * 
   * @param address The address of the checkout data.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Retrieves the address of the checkout data.
   * 
   * @return The address of the checkout data. 
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the city of the checkout data.
   * 
   * @param city The city of the checkout data.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Retrieves the city of the checkout data.
   * 
   * @return The city of the checkout data. 
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the state of the checkout data.
   * 
   * @param state The state of the checkout data.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Retrieves the state of the checkout data.
   * 
   * @return The state of the checkout data. 
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the country of the checkout data.
   * 
   * @param country The country of the checkout data.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Retrieves the country of the checkout data.
   * 
   * @return The country of the checkout data. 
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the zipCode of the checkout data.
   * 
   * @param zipCode The zipCode of the checkout data.
   */ 
  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Retrieves the zipCode of the checkout data.
   * 
   * @return The zipCode of the checkout data. 
   */
  public int getZipCode() {
    return zipCode;
  }

  /**
   * Sets the email of the checkout data.
   * 
   * @param email The email of the checkout data.
   */ 
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Retrieves the email of the checkout data.
   * 
   * @return The email of the checkout data. 
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the phoneNumber of the checkout data.
   * 
   * @param phoneNumber The phoneNumber of the checkout data.
   */ 
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Retrieves the phoneNumber of the checkout data.
   * 
   * @return The phoneNumber of the checkout data. 
   */
  public String getPhoneNumber() {
    return phoneNumber;
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
  public void setCreditCardExpiration(String expiration) {
    this.creditCardExpiration = expiration;
  } 

  /**
   * Gets the credit card expiration of the checkout data.
   * 
   * @return The credit card expiration of the checkout data.
   */
  public String getCreditCardExpiration() {
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
    return String.format(STRING_FORMAT, this.userID, this.firstName, this.lastName, this.address, this.city, this.state, this.country, this.zipCode, this.email, this.phoneNumber, this.creditCardNumber, this.creditCardExpiration, this.creditCardCVC, this.creditCardHolder, this.creditCardZipCode);
  } 
}

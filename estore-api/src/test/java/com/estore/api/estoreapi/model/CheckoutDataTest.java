package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test class for the CheckoutData class.
 */
@Tag("Model-tier")
public class CheckoutDataTest {
  /* The checkout data object used for all tests. */
  private CheckoutData checkoutData;
  /* The expected User ID for the checkout data object. */
  private final int expectedUserID = 0;
  /* The expected First name for the checkout data object. */
  private final String expectedFirstName = "Siddhartha";
  /* The expected last name for the checkout data object. */
  private final String expectedLastName = "Juluru";
  /* The expected address for the checkout data object. */
  private final String expectedAddress = "1 Lomb Memorial Drive";
  /* The expected city for the checkout data object. */
  private final String expectedCity = "Rochester";
  /* The expected state for the checkout data object. */
  private final String expectedState = "NY";
  /* The expected country for the checkout data object. */
  private final String expectedCountry = "United States";
  /* The expected zip code for the checkout data object. */
  private final int expectedZipCode = 14623;
  /* The expected email for the checkout data object. */
  private final String expectedEmail = "ssj4651@rit.edu";
  /* The expected phone number for the checkout data object. */
  private final String expectedPhoneNumber = "+1 (800) 588-2300";
  /* The expected credit card number for the checkout data object. */
  private final String expectedCreditCardNumber = "1234123412341234";
  /* The expected credit card expiration for the checkout data object. */
  private Date expectedCreditCardDate;
  /* The expected credit card CVC for the checkout data object. */
  private final int expectedCreditCardCVC = 123;
  /* The expected credit card holder for the checkout data object. */
  private final String expectedCreditCardHolder = "John Doe";
  /* The expected credit card zip code for the checkout data object. */
  private final int expectedCreditCardZipCode = 12345;
  /* The expected string for the checkout data object. */
  private final String expectedToString = "CheckoutData [userID=0, firstName=Siddhartha, lastName=Juluru, address=1 Lomb Memorial Drive, city=Rochester, state=NY, country=United States, zipCode=14623, email=ssj4651@rit.edu, phoneNumber=+1 (800) 588-2300, creditCardNumber=1234123412341234, creditCardExpiration=12/21, creditCardCVC=123, creditCardHolder=John Doe, creditCardZipCode=12345]";

  @BeforeEach
  void setupDate() {
    try {
      this.expectedCreditCardDate = new SimpleDateFormat("MM/yy").parse("12/21");
    } catch (Exception e) {
    }

    this.checkoutData = new CheckoutData(expectedUserID, expectedFirstName, expectedLastName, expectedAddress, expectedCity, expectedState, expectedCountry, expectedZipCode, expectedEmail, expectedPhoneNumber, expectedCreditCardNumber, expectedCreditCardDate, expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
  }

  /**
   * Tests whether the CheckoutData object is constructed properly.
   */
  @Test
  void testConstruction() {
    assertEquals(checkoutData.getUserID(), expectedUserID);
    assertEquals(checkoutData.getFirstName(), expectedFirstName);
    assertEquals(checkoutData.getLastName(), expectedLastName);
    assertEquals(checkoutData.getAddress(), expectedAddress);
    assertEquals(checkoutData.getCity(), expectedCity);
    assertEquals(checkoutData.getState(), expectedState);
    assertEquals(checkoutData.getCountry(), expectedCountry);
    assertEquals(checkoutData.getZipCode(), expectedZipCode);
    assertEquals(checkoutData.getEmail(), expectedEmail);
    assertEquals(checkoutData.getPhoneNumber(), expectedPhoneNumber);
    assertEquals(checkoutData.getCreditCardNumber(), expectedCreditCardNumber);
    assertEquals(checkoutData.getCreditCardExpiration(), expectedCreditCardDate);
    assertEquals(checkoutData.getCreditCardCVC(), expectedCreditCardCVC);
    assertEquals(checkoutData.getCreditCardHolder(), expectedCreditCardHolder);
    assertEquals(checkoutData.getCreditCardZipCode(), expectedCreditCardZipCode);
  }

  /**
   * Tests whether the checkout data user id is set properly.
   */
  @Test
  void testSetUserID() {
    int newUserID = 1;
    checkoutData.setUserID(newUserID);
    assertEquals(checkoutData.getUserID(), newUserID);
  }

  /**
   * Tests whether the checkout data first name is set properly.
   */
  @Test
  void testSetFirstName() {
    String newFirstName = "John";
    checkoutData.setFirstName(newFirstName);
    assertEquals(checkoutData.getFirstName(), newFirstName);
  }

  /**
   * Tests whether the checkout data last name is set properly.
   */
  @Test
  void testSetLastName() {
    String newLastName = "Doe";
    checkoutData.setLastName(newLastName);
    assertEquals(checkoutData.getLastName(), newLastName);
  }

  /**
   * Tests whether the checkout data address is set properly.
   */
  @Test
  void testSetAddress() {
    String newAddress = "188 Honey Creek Drive";
    checkoutData.setAddress(newAddress);
    assertEquals(checkoutData.getAddress(), newAddress);
  }

  /**
   * Tests whether the checkout data city is set properly.
   */
  @Test
  void testSetCity() {
    String newCity = "Cary";
    checkoutData.setCity(newCity);
    assertEquals(checkoutData.getCity(), newCity);
  }

  /**
   * Tests whether the checkout data state is set properly.
   */
  @Test
  void testSetState() {
    String newState = "NC";
    checkoutData.setState(newState);
    assertEquals(checkoutData.getState(), newState);
  }

  /**
   * Tests whether the checkout data country is set properly.
   */
  @Test
  void testSetCountry() {
    String newCountry = "United States";
    checkoutData.setCountry(newCountry);
    assertEquals(checkoutData.getCountry(), newCountry);
  }

  /**
   * Tests whether the checkout data zip code is set properly.
   */
  @Test
  void testSetZipCode() {
    int newZipCode = 27511;
    checkoutData.setZipCode(newZipCode);
    assertEquals(checkoutData.getZipCode(), newZipCode);
  }

  /**
   * Tests whether the checkout data email is set properly.
   */
  @Test
  void testSetEmail() {
    String newEmail = "idk@example.com";
    checkoutData.setEmail(newEmail);
    assertEquals(checkoutData.getEmail(), newEmail);
  }

  /**
   * Tests whether the checkout data phone number is set properly.
   */
  @Test
  void testSetPhoneNumber() {
    String newNumber = "911";
    checkoutData.setPhoneNumber(newNumber);
    assertEquals(checkoutData.getPhoneNumber(), newNumber);
  }

  /**
   * Tests whether the checkout data credit card number is set properly.
   */
  @Test
  void testSetCreditCardNumber() {
    String newCreditCardNumber = "5678567856785678";
    checkoutData.setCreditCardNumber(newCreditCardNumber);
    assertEquals(checkoutData.getCreditCardNumber(), newCreditCardNumber);
  }

  /**
   * Tests whether the checkout data expiration is set properly.
   */
  @Test
  void testCreditCardExpiration() {
    Date newDate = null;
    try {
      newDate = new SimpleDateFormat("MM/yy").parse("56/78");
    } catch (Exception e) {
    }

    checkoutData.setCreditCardExpiration(newDate);
    assertEquals(checkoutData.getCreditCardExpiration(), newDate);

  }

  /**
   * Tests whether the checkout data cvc is set properly.
   */
  @Test
  void testCreditCardCVC() {
    int newCVC = 150;
    checkoutData.setCreditCardCVC(newCVC);
    assertEquals(checkoutData.getCreditCardCVC(), newCVC);
  }

  /**
   * Tests whether the checkout data holder is set properly.
   */
  @Test
  void testCreditCardHolder() {
    String newHolder = "Jane Doe";
    checkoutData.setCreditCardHolder(newHolder);
    assertEquals(checkoutData.getCreditCardHolder(), newHolder);
  }

  /**
   * Tests whether the checkout data zip code is set properly.
   */
  @Test
  void testCreditCardZipCode() {
    int newZipCode = 15043;
    checkoutData.setCreditCardZipCode(newZipCode);
    assertEquals(checkoutData.getCreditCardZipCode(), newZipCode);
  }

  /**
   * Tests whether the checkout data to string is displaying properly.
   */
  @Test
  void testToString() {
    assertEquals(checkoutData.toString(), expectedToString);
  }
}

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
  /* The expected User ID for the checkout data object. */
  private final int expectedUserID = 1;
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
  private final String expectedToString = "CheckoutData [userID=1, creditCardNumber=1234123412341234, creditCardExpiration=12/21, creditCardCVC=123, creditCardHolder=John Doe, creditCardZipCode=12345]";

  @BeforeEach
  void setupDate() {
    try {
      this.expectedCreditCardDate = new SimpleDateFormat("MM/yy").parse("12/21");
    } catch (Exception e) {
    }
  }

  /**
   * Tests whether the CheckoutData object is constructed properly.
   */
  @Test
  void testConstruction() {

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    assertEquals(checkoutData.getUserID(), expectedUserID);
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

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    int newUserID = 2;
    checkoutData.setUserID(newUserID);
    assertEquals(checkoutData.getUserID(), newUserID);
  }

  /**
   * Tests whether the checkout data credit card number is set properly.
   */
  @Test
  void testSetCreditCardNumber() {

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    String newCreditCardNumber = "5678567856785678";
    checkoutData.setCreditCardNumber(newCreditCardNumber);
    assertEquals(checkoutData.getCreditCardNumber(), newCreditCardNumber);
  }

  /**
   * Tests whether the checkout data expiration is set properly.
   */
  @Test
  void testCreditCardExpiration() {

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

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

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    int newCVC = 150;
    checkoutData.setCreditCardCVC(newCVC);
    assertEquals(checkoutData.getCreditCardCVC(), newCVC);
  }

  /**
   * Tests whether the checkout data holder is set properly.
   */
  @Test
  void testCreditCardHolder() {

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    String newHolder = "Jane Doe";
    checkoutData.setCreditCardHolder(newHolder);
    assertEquals(checkoutData.getCreditCardHolder(), newHolder);
  }

  /**
   * Tests whether the checkout data zip code is set properly.
   */
  @Test
  void testCreditCardZipCode() {

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    int newZipCode = 15043;
    checkoutData.setCreditCardZipCode(newZipCode);
    assertEquals(checkoutData.getCreditCardZipCode(), newZipCode);
  }

  /**
   * Tests whether the checkout data to string is displaying properly.
   */
  @Test
  void testToString() {

    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);

    assertEquals(checkoutData.toString(), expectedToString);
  }
}

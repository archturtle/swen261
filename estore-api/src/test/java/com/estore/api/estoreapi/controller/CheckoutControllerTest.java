package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.CheckoutData;
import com.estore.api.estoreapi.model.Keyboard;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.KeyboardFileDAO;
import com.estore.api.estoreapi.persistence.UserFileDAO;

@Tag("Controller-tier")
public class CheckoutControllerTest {
  private CheckoutController checkoutController;
  private UserFileDAO mockUserFileDAO;
  private KeyboardFileDAO mockKeyboardFileDAO;

  /* The expected User ID for the checkout data object. */
  private final int expectedUserID = 0;
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

  @BeforeEach
  void setupKeyboardController() {
    mockKeyboardFileDAO = mock(KeyboardFileDAO.class);
    mockUserFileDAO = mock(UserFileDAO.class);
    checkoutController = new CheckoutController(mockKeyboardFileDAO, mockUserFileDAO);

    try {
      this.expectedCreditCardDate = new SimpleDateFormat("MM/yy").parse("12/27");
    } catch (Exception e) { }
  }

  @Test
  void testCheckoutFailsExpirationPassed() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, new Date(0),
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardLengthLessThan16() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, "", this.expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardCVCLessThan100() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        50, expectedCreditCardHolder, expectedCreditCardZipCode);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardCVCGreaterThan999() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        1000, expectedCreditCardHolder, expectedCreditCardZipCode);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardZipCodeLessThan10000() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, 9999);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardZipCodeGreaterThan99999() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, 100000);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardHolderEmpty() {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        expectedCreditCardCVC, "", expectedCreditCardZipCode);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsUserDoesntExist() throws IOException {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
    when(mockUserFileDAO.findByID(expectedUserID)).thenReturn(null);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void testCheckoutSucceeds() throws IOException {
    User user = new User(expectedUserID, "Siddhartha", 1);
    user.addToCart(0);
    user.addToCart(0);
    user.addToCart(0);
    user.addToCart(0);
    user.addToCart(1);
    user.addToCart(1);
    user.addToCart(1);

    Keyboard keyboard = new Keyboard(0, "GMMK PRO", 119.99, "It's a keyboard", 200);
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
        expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
    when(mockUserFileDAO.findByID(0)).thenReturn(user);
    when(mockKeyboardFileDAO.findByID(0)).thenReturn(keyboard);
    when(mockKeyboardFileDAO.findByID(1)).thenReturn(null);

    Keyboard newKeyboard = new Keyboard(keyboard.getId(), keyboard.getName(), keyboard.getPrice(), keyboard.getDescription(), keyboard.getQuantity() - 4);
    when(mockKeyboardFileDAO.update(keyboard)).thenReturn(newKeyboard);

    User newUser = new User(user.getId(), user.getName(), user.getRole());
    newUser.clearCart();
    when(mockUserFileDAO.update(user)).thenReturn(newUser);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(newUser, response.getBody());
  }

  @Test
  void testCheckoutFailsExceptionThrown() throws IOException {
    CheckoutData checkoutData = new CheckoutData(expectedUserID, expectedCreditCardNumber, this.expectedCreditCardDate,
    expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
    when(mockUserFileDAO.findByID(expectedUserID)).thenThrow(IOException.class);

    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); 
  } 
}

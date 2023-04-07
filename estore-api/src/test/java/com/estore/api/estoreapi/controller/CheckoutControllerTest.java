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
  private final String expectedCreditCardDate = "12/23";
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

    this.checkoutData = new CheckoutData(expectedUserID, expectedFirstName, expectedLastName, expectedAddress, expectedCity, expectedState, expectedCountry, expectedZipCode, expectedEmail, expectedPhoneNumber, expectedCreditCardNumber, expectedCreditCardDate, expectedCreditCardCVC, expectedCreditCardHolder, expectedCreditCardZipCode);
  }

  @Test
  void testCheckoutFailsFirstNameEmpty() {
    this.checkoutData.setFirstName("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsLastNameEmpty() {
    this.checkoutData.setLastName("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsAddressEmpty() {
    this.checkoutData.setAddress("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCityEmpty() {
    this.checkoutData.setCity("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsStateEmpty() {
    this.checkoutData.setState("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCountryEmpty() {
    this.checkoutData.setCountry("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsZipCodeLessThan100() {
    this.checkoutData.setZipCode(99);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsZipCodeGreaterThan99999() {
    this.checkoutData.setZipCode(100000);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsEmailEmpty() {
    this.checkoutData.setEmail("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsExpirationToShort() {
    this.checkoutData.setCreditCardExpiration("2/21");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsExpirationToLong() {
    this.checkoutData.setCreditCardExpiration("12/2021");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsExpirationPassed() {
    this.checkoutData.setCreditCardExpiration("12/21");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardLengthLessThan16() {
    this.checkoutData.setCreditCardNumber("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardCVCLessThan100() {
    this.checkoutData.setCreditCardCVC(50);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardCVCGreaterThan999() {
    this.checkoutData.setCreditCardCVC(1000);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardZipCodeLessThan100() {
    this.checkoutData.setCreditCardZipCode(99);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardZipCodeGreaterThan99999() {
    this.checkoutData.setCreditCardZipCode(100000);
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsCardHolderEmpty() {
    this.checkoutData.setCreditCardHolder("");
    
    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
  }

  @Test
  void testCheckoutFailsUserDoesntExist() throws IOException {
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
    when(mockUserFileDAO.findByID(expectedUserID)).thenThrow(IOException.class);

    ResponseEntity<User> response = checkoutController.checkout(checkoutData);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()); 
  } 
}

package com.estore.api.estoreapi.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.CheckoutData;
import com.estore.api.estoreapi.model.Keyboard;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.KeyboardFileDAO;
import com.estore.api.estoreapi.persistence.UserFileDAO;

@RestController
@RequestMapping("checkout")
public class CheckoutController {
   /**
   * The logger used to print out messages to standard out.
   */
  private static final Logger LOG = Logger.getLogger(CheckoutController.class.getName());
  /**
   * The {@linkplain KeyboardFileDAO Keyboard Data Access Object}. Look at
   * {@link CheckoutController#CheckoutController(KeyboardFileDAO, UserFileDAO)} for more
   * information on how this is set.
   */
  private KeyboardFileDAO keyboardDAO;

  /**
   * The {@linkplain UserFileDAO User Data Access Object}. Look at
   * {@link CheckoutController#CheckoutController(KeyboardFileDAO, UserFileDAO)} for more
   * information on how this is set.
   */
  private UserFileDAO userDAO;

  /**
   * Creates a REST API controller to reponds to requests
   *
   * @param keyboardDao The {@link KeyboardFileDAO Keyboard Data Access Object} to
   *                    perform CRUD operations
   *                    <br>
   *                    This dependency is injected by the Spring Framework
   * @param userDao The {@link UserFileDAO User Data Access Object} to
   *                    perform CRUD operations
   *                    <br>
   *                    This dependency is injected by the Spring Framework
   */
  public CheckoutController(KeyboardFileDAO keyboardDAO, UserFileDAO userDAO) {
    this.keyboardDAO = keyboardDAO;
    this.userDAO = userDAO;
  }

  @PostMapping("")
  public ResponseEntity<User> checkout(@RequestBody CheckoutData checkoutData) {
    LOG.log(Level.INFO,"POST /checkout {0}", checkoutData);

    try {
      if (checkoutData.getFirstName().length() < 1 || checkoutData.getLastName().length() < 1 ||
          checkoutData.getAddress().length() < 1 || checkoutData.getCity().length() < 1 ||
          checkoutData.getState().length() != 2 || checkoutData.getCountry().length() < 1 ||
          checkoutData.getZipCode() < 100 || checkoutData.getZipCode() > 99999 ||
          checkoutData.getEmail().length() < 1 ||
          checkoutData.getCreditCardExpiration().length() < 5 || 
          checkoutData.getCreditCardNumber().length() != 16 || 
          checkoutData.getCreditCardCVC() < 100 || checkoutData.getCreditCardCVC() > 999 ||
          checkoutData.getCreditCardZipCode() < 100 || checkoutData.getCreditCardZipCode() > 99999 || 
          checkoutData.getCreditCardHolder().length() == 0)
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
     
      Date expiration = new SimpleDateFormat("MM/yy").parse(checkoutData.getCreditCardExpiration());
      if (expiration.before(Date.from(Instant.now()))) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        
      User user = this.userDAO.findByID(checkoutData.getUserID());
      if (user == null) 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     
      Map<Integer, Integer> cartItems = user.getCart().stream()
        .collect(Collectors.toMap(Function.identity(), item -> 1, Math::addExact));
      
      for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
        Keyboard keyboard = this.keyboardDAO.findByID(entry.getKey());
        if (keyboard == null) continue;

        keyboard.setQuantity(keyboard.getQuantity() - entry.getValue());
        this.keyboardDAO.update(keyboard);
      }

      System.out.println(user);
      user.clearCart();
      System.out.println(user);
      User newUser = this.userDAO.update(user);
      return new ResponseEntity<>(newUser, HttpStatus.OK);
    } catch (Exception e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

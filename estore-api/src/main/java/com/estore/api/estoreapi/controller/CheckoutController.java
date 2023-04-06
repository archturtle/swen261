package com.estore.api.estoreapi.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.CheckoutData;
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
  public ResponseEntity<Void> checkout(@RequestBody CheckoutData checkoutData) {
    LOG.log(Level.INFO,"POST /checkout {0}", checkoutData);

    // try {
    //   Keyboard[] found = this.keyboardDAO.findByName(keyboard.getName());
    //   if (found.length != 0)
    //     return new ResponseEntity<>(HttpStatus.CONFLICT);

    //   Keyboard newKeyboard = this.keyboardDAO.create(keyboard);
    //   return new ResponseEntity<>(newKeyboard, HttpStatus.CREATED);
    // } catch (IOException e) {
    //   LOG.log(Level.SEVERE, e.getLocalizedMessage());
    //   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

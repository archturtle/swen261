package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Keyboard;
import com.estore.api.estoreapi.persistence.GenericDAO;

/**
 * Handles the REST API requests for the Keyboard resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST
 * API
 * method handler to the Spring framework
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
@RestController
@RequestMapping("keyboards")
public class KeyboardController {
  /**
   * The logger used to print out messages to standard out.
   */
  private static final Logger LOG = Logger.getLogger(KeyboardController.class.getName());
  /**
   * The {@linkplain KeyboardDAO Keyboard Data Access Object}. Look at
   * {@link KeyboardController#KeyboardController(KeyboardDAO)} for more
   * information on how this is set.
   */
  private GenericDAO<Keyboard> keyboardDAO;

  /**
   * Creates a REST API controller to reponds to requests
   *
   * @param keyboardDao The {@link KeyboardDAO Keyboard Data Access Object} to
   *                    perform CRUD operations
   *                    <br>
   *                    This dependency is injected by the Spring Framework
   */
  public KeyboardController(GenericDAO<Keyboard> keyboardDAO) {
    this.keyboardDAO = keyboardDAO;
  }

  /**
   * Responds to the GET request for a {@linkplain Keyboard keyboard} for the
   * given id
   *
   * @param id The id used to locate the {@link Keyboard keyboard}
   *
   * @return ResponseEntity with {@link Keyboard keyboard} object and HTTP status
   *         of OK if found<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @GetMapping("/{id}")
  public ResponseEntity<Keyboard> getKeyboard(@PathVariable int id) {
    LOG.info("GET /keyboards/" + id);

    try {
      Keyboard keyboard = this.keyboardDAO.findByID(id);
      if (keyboard == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      return new ResponseEntity<Keyboard>(keyboard, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Responds to the GET request for all {@linkplain Keyboard keyboards}
   *
   * @return ResponseEntity with array of {@link Keyboard keyboard} objects (may
   *         be empty) and
   *         HTTP status of OK<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @GetMapping("")
  public ResponseEntity<Keyboard[]> getKeyboards() {
    LOG.info("GET /keyboards");

    try {
      Keyboard[] keyboards = this.keyboardDAO.getAll();
      return new ResponseEntity<Keyboard[]>(keyboards, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Responds to the GET request for all {@linkplain Keyboard keyboards} whose
   * name contains
   * the text in name
   *
   * @param name The name parameter which contains the text used to find the
   *             {@link Keyboard keyboards}
   *
   * @return ResponseEntity with array of {@link Keyboards keyboard} objects (may
   *         be empty) and
   *         HTTP status of OK<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   *         <p>
   *         Example: Find all keyboards that contain the text "qk"
   *         GET http://localhost:8080/keyboards/?name=qk
   */
  @GetMapping("/")
  public ResponseEntity<Keyboard[]> searchKeyboards(@RequestParam String name) {
    LOG.info("GET /keyboards/?name=" + name);

    try {
      Keyboard[] keyboards = this.keyboardDAO.findByName(name);
      return new ResponseEntity<Keyboard[]>(keyboards, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Creates a {@linkplain Keyboard keyboard} with the provided keyboard object
   *
   * @param keyboard - The {@link Keyboard keyboard} to create
   *
   * @return ResponseEntity with created {@link Keyboard keyboard} object and HTTP
   *         status of CREATED<br>
   *         ResponseEntity with HTTP status of CONFLICT if {@link Keyboard
   *         keyboard} object already exists<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @PostMapping("")
  public ResponseEntity<Keyboard> createKeyboard(@RequestBody Keyboard keyboard) {
    LOG.info("POST /keyboards " + keyboard);

    try {
      Keyboard[] found = this.keyboardDAO.findByName(keyboard.getName());
      if (found.length != 0)
        return new ResponseEntity<>(HttpStatus.CONFLICT);

      Keyboard newKeyboard = this.keyboardDAO.create(keyboard);
      return new ResponseEntity<>(newKeyboard, HttpStatus.CREATED);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Updates the {@linkplain Keyboard keyboard} with the provided
   * {@linkplain Keyboard keyboard} object, if it exists
   *
   * @param keyboard The {@link Keyboard keyboard} to update
   *
   * @return ResponseEntity with updated {@link Keyboard keyboard} object and HTTP
   *         status of OK if updated<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @PutMapping("")
  public ResponseEntity<Keyboard> updateKeyboard(@RequestBody Keyboard keyboard) {
    LOG.info("PUT /keyboards " + keyboard);

    try {
      Keyboard found = this.keyboardDAO.findByID(keyboard.getId());
      if (found == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      Keyboard newKeyboard = this.keyboardDAO.update(keyboard);
      return new ResponseEntity<>(newKeyboard, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Deletes a {@linkplain Keyboard keyboard} with the given id
   *
   * @param id The id of the {@link Keyboard keyboard} to deleted
   *
   * @return ResponseEntity HTTP status of OK if deleted<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Keyboard> deleteKeyboard(@PathVariable int id) {
    LOG.info("DELETE /keyboards/" + id);

    try {
      Keyboard found = this.keyboardDAO.findByID(id);
      if (found == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      this.keyboardDAO.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

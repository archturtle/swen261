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

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserFileDAO;

@RestController
@RequestMapping("users")
public class UserController {
  /**
   * The logger used to print out messages to standard out.
   */
  private static final Logger LOG = Logger.getLogger(UserController.class.getName());
  /**
   * The {@linkplain UserFileDAO User Data Access Object}. Look at
   * {@link UserController#UserController(UserFileDAO)} for more
   * information on how this is set.
   */
  private UserFileDAO userDAO;

  /**
   * Creates a REST API controller to reponds to requests
   *
   * @param userDao The {@link UserDAO User Data Access Object} to
   *                    perform CRUD operations
   *                    <br>
   *                    This dependency is injected by the Spring Framework
   */
  public UserController(UserFileDAO userDAO) {
    this.userDAO = userDAO;
  }

  /**
   * Responds to the GET request for a {@linkplain User user} for the
   * given id
   *
   * @param id The id used to locate the {@link User user}
   *
   * @return ResponseEntity with {@link User user} object and HTTP status
   *         of OK if found<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable int id) {
    LOG.info("GET /users/" + id);

    try {
      User user = this.userDAO.findByID(id);
      if (user == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Responds to the GET request for all {@linkplain User users}
   *
   * @return ResponseEntity with array of {@link User user} objects (may
   *         be empty) and
   *         HTTP status of OK<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @GetMapping("")
  public ResponseEntity<User[]> getUsers() {
    LOG.info("GET /users");

    try {
      User[] users = this.userDAO.getAll();
      return new ResponseEntity<User[]>(users, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Responds to the GET request for all {@linkplain User users} whose
   * name contains
   * the text in name
   *
   * @param name The name parameter which contains the text used to find the
   *             {@link User users}
   *
   * @return ResponseEntity with array of {@link Users user} objects (may
   *         be empty) and
   *         HTTP status of OK<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   *         <p>
   *         Example: Find all users that contain the text "qk"
   *         GET http://localhost:8080/users/?name=qk
   */
  @GetMapping("/")
  public ResponseEntity<User[]> searchUsers(@RequestParam String name) {
    LOG.info("GET /users/?name=" + name);

    try {
      User[] users = this.userDAO.findByName(name);
      return new ResponseEntity<User[]>(users, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Creates a {@linkplain User user} with the provided user object
   *
   * @param user - The {@link User user} to create
   *
   * @return ResponseEntity with created {@link User user} object and HTTP
   *         status of CREATED<br>
   *         ResponseEntity with HTTP status of CONFLICT if {@link User
   *         user} object already exists<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    LOG.info("POST /users " + user);

    try {
      User[] found = this.userDAO.findByName(user.getName());
      if (found.length != 0)
        return new ResponseEntity<>(HttpStatus.CONFLICT);

      User newUser = this.userDAO.create(user);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Updates the {@linkplain User user} with the provided
   * {@linkplain User user} object, if it exists
   *
   * @param user The {@link User user} to update
   *
   * @return ResponseEntity with updated {@link User user} object and HTTP
   *         status of OK if updated<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @PutMapping("")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    LOG.info("PUT /users " + user);

    try {
      User found = this.userDAO.findByID(user.getId());
      if (found == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      User newUser = this.userDAO.update(user);
      return new ResponseEntity<>(newUser, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Deletes a {@linkplain User user} with the given id
   *
   * @param id The id of the {@link User user} to deleted
   *
   * @return ResponseEntity HTTP status of OK if deleted<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable int id) {
    LOG.info("DELETE /users/" + id);

    try {
      User found = this.userDAO.findByID(id);
      if (found == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      this.userDAO.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
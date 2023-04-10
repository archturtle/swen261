package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.estore.api.estoreapi.model.CartItem;
import com.estore.api.estoreapi.model.CustomKeyboard;
import com.estore.api.estoreapi.model.Keyboard;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.model.CartItem.Type;
import com.estore.api.estoreapi.persistence.KeyboardFileDAO;
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
   * The {@linkplain KeyboardFileDAO Keyboard Data Access Object}. Look at
   * {@link KeyboardController#KeyboardController(KeyboardFileDAO)} for more
   * information on how this is set.
   */
  private KeyboardFileDAO keyboardDAO;

  /**
   * Creates a REST API controller to reponds to requests
   *
   * @param userDao The {@link UserDAO User Data Access Object} to
   *                    perform CRUD operations
   *                    <br>
   *                    This dependency is injected by the Spring Framework
   * @param keyboardDao The {@link KeyboardFileDAO Keyboard Data Access Object} to
   *                    perform CRUD operations
   *                    <br>
   *                    This dependency is injected by the Spring Framework
   */
  public UserController(UserFileDAO userDAO, KeyboardFileDAO keyboardDAO) {
    this.userDAO = userDAO;
    this.keyboardDAO = keyboardDAO;
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
    LOG.log(Level.INFO,"GET /users/{0}", id);

    try {
      User user = this.userDAO.findByID(id);
      if (user == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(user, HttpStatus.OK);
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
    LOG.log(Level.INFO,"GET /users");

    try {
      User[] users = this.userDAO.getAll();
      return new ResponseEntity<>(users, HttpStatus.OK);
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
    LOG.log(Level.INFO,"GET /users/?name={0}", name);

    try {
      User[] users = this.userDAO.findByName(name);
      return new ResponseEntity<>(users, HttpStatus.OK);
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
    LOG.log(Level.INFO,"POST /users {0}", user);

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
   * Add a {@linkplain CartItem cartItem} to a {@linkplain User user} cart.
   *
   * @param userId - The id of the {@link User user} whose cart should be modified 
   *
   * @return ResponseEntity with updated {@link User user} object and HTTP
   *         status of OK<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if {@link User
   *         user} doesn't exist.<br>
   *         ResponseEntity with HTTP status of FORBIDDEN if {@link User
   *         user} is an admin.<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if {@link Keyboard
   *         keyboard} doesn't exist.<br>
   *         ResponseEntity with HTTP status of REQUESTED_RANGE_NOT_SATISFIABLE if 
   *         requested quantity cannot be satisfied.<br>
   *         ResponseEntity with HTTP status of BAD_REQUEST if CustomKeyboard is added
   *         with sending the keyboard or if quantity is less than 1.
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @PostMapping("/{userId}/cart")
  public ResponseEntity<User> addItemToCart(@PathVariable int userId, @RequestBody CartItem cartItem) {
    LOG.log(Level.INFO, "POST {0}", String.format("/%d/cart %s", userId, cartItem));

    try {
      User user = this.userDAO.findByID(userId);
      if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      if (user.getRole() == 0) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

      ArrayList<CartItem> userCart = new ArrayList<>(user.getCart());
      if (cartItem.getCartItemType() == CartItem.Type.STANDARD_KEYBOARD) {
        Keyboard keyboard = this.keyboardDAO.findByID(cartItem.getKeyboardID());
        if (keyboard == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (cartItem.getQuantity() < 1 || cartItem.getQuantity() > keyboard.getQuantity()) return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);

        CartItem itemInCart = userCart.stream()
          .filter(item -> item.getKeyboardID() == cartItem.getKeyboardID())
          .findFirst()
          .orElse(null);
        
        if (itemInCart == null) {
          userCart.add(cartItem);
        } else {
          if (itemInCart.getQuantity() + cartItem.getQuantity() > keyboard.getQuantity())
            return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        
          userCart.remove(itemInCart);
          cartItem.setQuantity(cartItem.getQuantity() + itemInCart.getQuantity());
          userCart.add(cartItem);
        }
      } else {
        if (cartItem.getCustomKeyboard() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (cartItem.getQuantity() < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userCart.stream()
          .filter(item -> item.getCartItemType() == Type.CUSTOM_KEYBOARD)
          .filter(item -> {
            CustomKeyboard kb = item.getCustomKeyboard();
            return kb.equals(cartItem.getCustomKeyboard());
          })
          .findFirst()
          .ifPresentOrElse(item -> {
            userCart.remove(item);
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            userCart.add(item);
          }, () -> {
            userCart.add(cartItem);
          });
      }

      user.setCart(userCart);
      User updatedUser = this.userDAO.update(user); 
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } catch (IOException e) {
      LOG.log(Level.SEVERE, e.getLocalizedMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Deletes a {@linkplain CartItem cartItem} to a {@linkplain User user} cart.
   *
   * @param userId - The id of the {@link User user} whose cart should be modified 
   *
   * @return ResponseEntity with updated {@link User user} object and HTTP
   *         status of OK<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if {@link User
   *         user} doesn't exist.<br>
   *         ResponseEntity with HTTP status of FORBIDDEN if {@link User
   *         user} is an admin.<br>
   *         ResponseEntity with HTTP status of NOT_FOUND if {@link Keyboard
   *         keyboard} doesn't exist.<br>
   *         ResponseEntity with HTTP status of REQUESTED_RANGE_NOT_SATISFIABLE if 
   *         requested quantity cannot be satisfied.<br>
   *         ResponseEntity with HTTP status of BAD_REQUEST if CustomKeyboard is removed
   *         with sending the keyboard or if quantity is less than 1.
   *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
   */
  @DeleteMapping("/{userId}/cart")
  public ResponseEntity<User> removeItemFromCart(@PathVariable int userId, @RequestBody CartItem cartItem) {
    LOG.log(Level.INFO, "DELETE {0}", String.format("/%d/cart %s", userId, cartItem));

    try {
      User user = this.userDAO.findByID(userId);
      if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      if (user.getRole() == 0) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

      ArrayList<CartItem> userCart = new ArrayList<>(user.getCart());
      if (cartItem.getCartItemType() == CartItem.Type.STANDARD_KEYBOARD) {
        Keyboard keyboard = this.keyboardDAO.findByID(cartItem.getKeyboardID());
        if (keyboard == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (cartItem.getQuantity() < 1) return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);

        userCart.stream()
          .filter(item -> item.getKeyboardID() == cartItem.getKeyboardID())
          .findFirst()
          .ifPresent(item -> {
            userCart.remove(item);
            int range = (cartItem.getQuantity() > item.getQuantity()) ? item.getQuantity() : cartItem.getQuantity();
            if (item.getQuantity() - range != 0) {
              cartItem.setQuantity(item.getQuantity() - range);
              userCart.add(cartItem);
            }
          });
      } else {
        if (cartItem.getCustomKeyboard() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (cartItem.getQuantity() < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        userCart.stream()
        .filter(item -> item.getCartItemType() == CartItem.Type.CUSTOM_KEYBOARD)
        .filter(item -> {
          CustomKeyboard kb = item.getCustomKeyboard();
          return kb.equals(cartItem.getCustomKeyboard());
        })
        .findFirst()
        .ifPresent(item -> {
          userCart.remove(item);
          int range = (cartItem.getQuantity() > item.getQuantity()) ? item.getQuantity() : cartItem.getQuantity();
          if (item.getQuantity() - range != 0) {
            cartItem.setQuantity(item.getQuantity() - range);
            userCart.add(cartItem);
          }
        });
      }

      user.setCart(userCart);
      User updatedUser = this.userDAO.update(user); 
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
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
    LOG.log(Level.INFO, "PUT /users {0}", user);

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
    LOG.log(Level.INFO, "DELETE /users/{0}", id);

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

package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Keyboard;

/**
 * Defines the interface for Keyboard object persistence
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
public interface KeyboardDAO {
  /**
   * Retrieves all {@linkplain Keyboard keyboards}.
   *
   * @return An array of {@link Keyboard keyboard} objects, may be empty.
   * @throws IOException if an issue with underlying storage.
   */
  Keyboard[] getAllKeyboards() throws IOException;

  /**
   * Finds all {@linkplain Keyboard keyboards} whose name contains the given text.
   *
   * @param containsText The text to match against.
   * 
   * @return An array of {@link Keyboard keyboard} objects whose nemes contains
   *         the given text, may be empty.
   * @throws IOException if an issue with underlying storage.
   */
  Keyboard[] findKeyboardsByName(String containsText) throws IOException;

  /**
   * Retrieves a {@linkplain Keyboard keyboard} with the given id.
   *
   * @param id The id of the {@link Keyboard keyboard} to get.
   *
   * @return a {@link Keyboard keyboard} object with the matching id.
   *         <br>
   *         null if no {@link Keyboard keyboard} with a matching id is found.
   * @throws IOException if an issue with underlying storage.
   */
  Keyboard getKeyboardById(int id) throws IOException;

  /**
   * Creates and saves a {@linkplain Keyboard keyboard}.
   *
   * @param keyboard {@linkplain Keyboard keyboard} object to be created and
   *                 saved.
   *                 <br>
   *                 The id of the keyboard object is ignored and a new uniqe id
   *                 is assigned.
   *
   * @return new {@link Keyboard keyboard} if successful, false otherwise.
   * @throws IOException if an issue with underlying storage.
   */
  Keyboard createKeyboard(Keyboard keyboard) throws IOException;

  /**
   * Updates and saves a {@linkplain Keyboard keyboard}.
   *
   * @param {@link Keyboard keyboard} object to be updated and saved.
   *
   * @return updated {@link Keyboard keyboard} if successful, null if
   *         {@link Keyboard keyboard} could not be found.
   * @throws IOException if underlying storage cannot be accessed
   */
  Keyboard updateKeyboard(Keyboard keyboard) throws IOException;

  /**
   * Deletes a {@linkplain Keyboard keyboard} with the given id.
   *
   * @param id The id of the {@link Keyboard keyboard}
   *
   * @return true if the {@link Keyboard keyboard} was deleted
   *         <br>
   *         false if hero with the given id does not exist
   * @throws IOException if underlying storage cannot be accessed
   */
  boolean deleteKeyboard(int id) throws IOException;
}

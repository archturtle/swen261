package com.estore.api.estoreapi.persistence;

import java.io.IOException;

/**
 * Defines the interface for all DAO objects.
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
public interface GenericDAO<T> {
  /**
   * Retrieves all objects of type {@link T}.
   *
   * @return An array of type {@link T} objects, may be empty.
   * @throws IOException if an issue with underlying storage.
   */
  T[] getAll() throws IOException;

  /**
   * Finds all type {@link T} objects whose name contains the given text.
   *
   * @param containsText The text to match against.
   * 
   * @return An array of type {@link T} objects whose nemes contains
   *         the given text, may be empty.
   * @throws IOException if an issue with underlying storage.
   */
  T[] findByName(String containsText) throws IOException;

  /**
   * Retrieves a type {@link T} object with the given id.
   *
   * @param id The id of the type {@link T} object to get.
   *
   * @return a type {@link T} object with the matching id.
   *         <br>
   *         null if no type {@link T} object with a matching id is found.
   * @throws IOException if an issue with underlying storage.
   */
  T findByID(int id) throws IOException;

  /**
   * Creates and saves a type {@link T} object.
   *
   * @param obj Type {@link T} object to be created and saved.
   *            <br>
   *            The id of the type {@link T} object is ignored and a new unique id
   *            is assigned.
   *
   * @return new type {@link T} object if successful, false otherwise.
   * @throws IOException if an issue with underlying storage.
   */
  T create(T obj) throws IOException;

  /**
   * Updates and saves a type {@link T} object.
   *
   * @param obj Type {@link T} object to be updated and saved.
   *
   * @return updated type {@link T} object if successful, null if type
   *         {@link T} object could not be found.
   * @throws IOException if underlying storage cannot be accessed
   */
  T update(T obj) throws IOException;

  /**
   * Deletes a type {@link T} object with the given id.
   *
   * @param id The id of the type {@link T} object.
   *
   * @return the type {@link T} object if it was successfully deleted
   *         <br>
   *         null if it wasn't.
   * @throws IOException if underlying storage cannot be accessed
   */
  T delete(int id) throws IOException;
}
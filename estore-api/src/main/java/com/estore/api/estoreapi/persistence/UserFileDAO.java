package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the functionality for JSON file-based peristance for User
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of
 * this class and injects the instance into other classes as needed
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
@Component
public class UserFileDAO implements GenericDAO<User> {
   /**
   * The next id to use for a {@linkplain User user} object.
   */
  private static int nextId;
  /**
   * The local cache all the {@linkplain User user} objects.
   */
  Map<Integer, User> users;
  /**
   * The object mapper which helps turn a {@linkplain User user} object
   * into JSON.
   */
  private ObjectMapper objectMapper;
  /**
   * The filename for which data should be stored in. This is automatically
   * populated based on whatever is listed in application.properties.
   */
  private String filename;

  /**
   * Creates a User File Data Access Object
   *
   * @param filename     Filename to read from and write to
   * @param objectMapper Provides JSON Object to/from Java Object serialization
   *                     and deserialization
   *
   * @throws IOException when file cannot be accessed or read from
   */
  public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
    this.filename = filename;
    this.objectMapper = objectMapper;

    loadData();
  }

  /**
   * Generates the next id for a new {@linkplain User user}
   *
   * @return The next id
   */
  private synchronized static int nextId() {
    int id = nextId;
    ++nextId;

    return id;
  }

  /**
   * Loads {@linkplain User users} from the JSON file into the map
   * <br>
   * Also sets next id to one more than the greatest id found in the file
   *
   * @return true if the file was read successfully
   *
   * @throws IOException when file cannot be accessed or read from
   */
  private boolean loadData() throws IOException {
    this.users = new TreeMap<>();
    nextId = 0;

    // Deserializes the JSON objects from the file into an array of users
    // readValue will throw an IOException if there's an issue with the file
    // or reading from the file
    User[] userArray = objectMapper.readValue(new File(filename), User[].class);

    // Add each user to the tree map and keep track of the greatest id
    for (User user : userArray) {
      this.users.put(user.getId(), user);

      if (user.getId() > nextId) {
        nextId = user.getId();
      }
    }

    // Make the next id one greater than the maximum from the file
    ++nextId;
    return true;
  }

  /**
   * Saves the {@linkplain User users} from the map into the file as an
   * array of JSON objects
   *
   * @return true if the {@link User users} were written successfully
   *
   * @throws IOException when file cannot be accessed or written to
   */
  private boolean saveData() throws IOException {
    User[] userArray = getUsers(null);

    // Serializes the Java Objects to JSON objects into the file
    // writeValue will thrown an IOException if there is an issue
    // with the file or reading from the file
    this.objectMapper.writeValue(new File(filename), userArray);
    return true;
  }

  /**
   * Finds all the {@linkplain User user} objects whose name contains a
   * certain string. If null is passed, every {@linkplain User user} object 
   * is returned.
   * 
   * @param containsText The text that should be matched against.
   * 
   * @return An array of {@linkplain User user} objects that contain the
   *         text. It can be empty.
   */
  private User[] getUsers(String containsText) {
    ArrayList<User> foundUsers = new ArrayList<>();
    for (User user : this.users.values()) {
      if (containsText == null || user.getName().contains(containsText)) {
        foundUsers.add(user);
      }
    }

    return foundUsers.toArray(new User[0]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User[] getAll() throws IOException {
    synchronized (this.users) {
      return this.getUsers(null);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User[] findByName(String containsText) throws IOException {
    synchronized (this.users) {
      return this.getUsers(containsText);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User findByID(int id) throws IOException {
    synchronized (this.users) {
      if (!this.users.containsKey(id))
        return null;
      return this.users.get(id);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User create(User obj) throws IOException {
    synchronized (this.users) {
      User newUser = new User(nextId(), obj.getName(), obj.getRole());
      this.users.put(newUser.getId(), newUser);

      saveData(); // may throw an IOException
      return newUser;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User update(User obj) throws IOException {
    synchronized (this.users) {
      if (this.users.containsKey(obj.getId()) == false)
        return null;

      this.users.put(obj.getId(), obj);
      saveData(); // may throw an IOException
      return obj;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean delete(int id) throws IOException {
    synchronized (this.users) {
      if (!this.users.containsKey(id))
        return false;

      this.users.remove(id);
      return saveData();
    }
  } 
}

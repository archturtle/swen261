package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Keyboard;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the functionality for JSON file-based peristance for Keyboard
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of
 * this class and injects the instance into other classes as needed
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
@Component
public class KeyboardFileDAO implements GenericDAO<Keyboard> {
  /**
   * The next id to use for a {@linkplain Keyboard keyboard} object.
   */
  private static int nextId;
  /**
   * The local cache all the {@linkplain Keyboard keyboard} objects.
   */
  Map<Integer, Keyboard> keyboards;
  /**
   * The object mapper which helps turn a {@linkplain Keyboard keyboard} object
   * into JSON.
   */
  private ObjectMapper objectMapper;
  /**
   * The filename for which data should be stored in. This is automatically
   * populated based on whatever is listed in application.properties.
   */
  private String filename;

  /**
   * Creates a Keyboard File Data Access Object
   *
   * @param filename     Filename to read from and write to
   * @param objectMapper Provides JSON Object to/from Java Object serialization
   *                     and deserialization
   *
   * @throws IOException when file cannot be accessed or read from
   */
  public KeyboardFileDAO(@Value("${keyboards.file}") String filename, ObjectMapper objectMapper) throws IOException {
    this.filename = filename;
    this.objectMapper = objectMapper;

    loadData();
  }

  /**
   * Generates the next id for a new {@linkplain Keyboard keyboard}
   *
   * @return The next id
   */
  private synchronized static int nextId() {
    int id = nextId;
    ++nextId;

    return id;
  }

  /**
   * Loads {@linkplain Keyboard keyboards} from the JSON file into the map
   * <br>
   * Also sets next id to one more than the greatest id found in the file
   *
   * @return true if the file was read successfully
   *
   * @throws IOException when file cannot be accessed or read from
   */
  private boolean loadData() throws IOException {
    keyboards = new TreeMap<>();
    nextId = 0;

    // Deserializes the JSON objects from the file into an array of keyboards
    // readValue will throw an IOException if there's an issue with the file
    // or reading from the file
    Keyboard[] keyboardArray = objectMapper.readValue(new File(filename), Keyboard[].class);

    // Add each keyboard to the tree map and keep track of the greatest id
    for (Keyboard keyboard : keyboardArray) {
      keyboards.put(keyboard.getId(), keyboard);

      if (keyboard.getId() > nextId) {
        nextId = keyboard.getId();
      }
    }

    // Make the next id one greater than the maximum from the file
    ++nextId;
    return true;
  }

  /**
   * Saves the {@linkplain Keyboard keyboards} from the map into the file as an
   * array of JSON objects
   *
   * @return true if the {@link Keyboard keyboards} were written successfully
   *
   * @throws IOException when file cannot be accessed or written to
   */
  private boolean saveData() throws IOException {
    Keyboard[] keyboardArray = getKeyboards(null);

    // Serializes the Java Objects to JSON objects into the file
    // writeValue will thrown an IOException if there is an issue
    // with the file or reading from the file
    objectMapper.writeValue(new File(filename), keyboardArray);
    return true;
  }

  /**
   * Finds all the {@linkplain Keyboard keyboard} objects whose name contains a
   * certain string. If null is passed, every {@linkplain Keyboard keyboard} object 
   * is returned.
   * 
   * @param containsText The text that should be matched against.
   * 
   * @return An array of {@linkplain Keyboard keyboard} objects that contain the
   *         text. It can be empty.
   */
  private Keyboard[] getKeyboards(String containsText) {
    ArrayList<Keyboard> foundKeyboard = new ArrayList<>();
    for (Keyboard keyboard : keyboards.values()) {
      if (containsText == null || keyboard.getName().contains(containsText)) {
        foundKeyboard.add(keyboard);
      }
    }

    return foundKeyboard.toArray(new Keyboard[0]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Keyboard[] getAll() throws IOException {
    synchronized (keyboards) {
      return getKeyboards(null);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Keyboard[] findByName(String containsText) throws IOException {
    synchronized (keyboards) {
      return getKeyboards(containsText);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Keyboard findByID(int id) throws IOException {
    synchronized (keyboards) {
      if (!keyboards.containsKey(id))
        return null;
      return keyboards.get(id);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Keyboard create(Keyboard obj) throws IOException {
    synchronized (keyboards) {
      Keyboard newKeyboard = new Keyboard(nextId(), obj.getName(), obj.getPrice(), obj.getQuantity());
      keyboards.put(newKeyboard.getId(), newKeyboard);

      saveData(); // may throw an IOException
      return newKeyboard;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Keyboard update(Keyboard obj) throws IOException {
    synchronized (keyboards) {
      if (keyboards.containsKey(obj.getId()) == false)
        return null;

      keyboards.put(obj.getId(), obj);
      saveData(); // may throw an IOException
      return obj;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean delete(int id) throws IOException {
    synchronized (keyboards) {
      if (!keyboards.containsKey(id))
        return false;

      keyboards.remove(id);
      return saveData();
    }
  }
}

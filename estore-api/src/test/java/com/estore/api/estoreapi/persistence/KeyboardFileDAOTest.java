package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Keyboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * test the Keyboard File DAO class
 * 
 * @author Cathy Liu (cl6606@rit.edu)
 */
@Tag("Persistence-tier")
public class KeyboardFileDAOTest {
  KeyboardFileDAO keyboardFileDAO;
  Keyboard[] testKeyboards;
  ObjectMapper mockObjectMapper;

  /**
   * before each test, we will create and inject a Mock Object Mapper to
   * isolate the tests from the underlying file
   * @throws IOException
   */
  @BeforeEach
  public void setupKeyboardFileDAO() throws IOException {
    mockObjectMapper = mock(ObjectMapper.class);
    testKeyboards = new Keyboard[3];
    testKeyboards[0] = new Keyboard(99, "Apple", 250, 10);
    testKeyboards[1] = new Keyboard(100,"Banana", 100, 1);
    testKeyboards[2] = new Keyboard(101,"Orange", 150, 15);

    // when the object mapper is supposed to read from the file
    // the mock object mapper will return the keyboard array above
    when(mockObjectMapper
      .readValue(new File("doesnt_matter.txt"), Keyboard[].class))
          .thenReturn(testKeyboards);
      keyboardFileDAO = new KeyboardFileDAO("doesnt_matter.txt", mockObjectMapper);
  }

  @Test
  public void testGetKeyboards() throws IOException {
      // invoke
      Keyboard[] keyboards = keyboardFileDAO.getAll();

      // analyze
      assertEquals(keyboards.length, testKeyboards.length);
      for (int i = 0; i < testKeyboards.length;++i)
          assertEquals(keyboards[i], testKeyboards[i]);
  }

  @Test
  public void testFindKeyboards() throws IOException {
      // invoke
      Keyboard[] keyboards = keyboardFileDAO.findByName("an");

      // analyze
      assertEquals(keyboards.length, 2);
      assertEquals(keyboards[0], testKeyboards[1]);
      assertEquals(keyboards[1], testKeyboards[2]);
  }

  @Test
  public void testGetKeyboard() throws IOException {
      // invoke
      Keyboard keyboard = keyboardFileDAO.findByID(99);

      // analzye
      assertEquals(keyboard, testKeyboards[0]);
  }

  @Test
  public void testDeleteKeyboard() {
      // invoke
      boolean result = assertDoesNotThrow(() -> keyboardFileDAO.delete(99),
                          "Unexpected exception thrown");

      // analzye
      assertEquals(result, true);

      // we check the internal tree map size against the length
      // of the test keyboards array - 1 (because of the delete)
      // because keyboards attribute of KeyboardFileDAO is package private
      // we can access it directly
      assertEquals(keyboardFileDAO.keyboards.size(), testKeyboards.length-1);
  }

  @Test
  public void testCreateKeyboard() throws IOException {
      // setup
      Keyboard keyboard = new Keyboard(102, "Pear", 300, 20);

      // invoke
      Keyboard result = assertDoesNotThrow(() -> keyboardFileDAO.create(keyboard),
                              "Unexpected exception thrown");

      // analyze
      assertNotNull(result);
      Keyboard actual = keyboardFileDAO.findByID(keyboard.getId());
      assertEquals(actual.getId(), keyboard.getId());
      assertEquals(actual.getName(), keyboard.getName());
  }

  @Test
  public void testUpdateKeyboard() throws IOException {
      // setup
      Keyboard keyboard = new Keyboard(99, "Apple", 250, 10);

      // invoke
      Keyboard result = assertDoesNotThrow(() -> keyboardFileDAO.update(keyboard),
                              "Unexpected exception thrown");

      // analyze
      assertNotNull(result);
      Keyboard actual = keyboardFileDAO.findByID(keyboard.getId());
      assertEquals(actual, keyboard);
  }

  @Test
  public void testSaveException() throws IOException{
      doThrow(new IOException())
          .when(mockObjectMapper)
              .writeValue(any(File.class), any(Keyboard[].class));

      Keyboard keyboard = new Keyboard(105, "Sock", 1, 1);

      assertThrows(IOException.class,
                      () -> keyboardFileDAO.create(keyboard),
                      "IOException not thrown");
  }

  @Test
  public void testGetKeyboardNotFound() throws IOException {
      // invoke
      Keyboard keyboard = keyboardFileDAO.findByID(98);

      // analyze
      assertEquals(keyboard, null);
  }

  @Test
  public void testDeleteKeyboardNotFound() {
      // invoke
      boolean result = assertDoesNotThrow(() -> keyboardFileDAO.delete(98),
                                                    "Unexpected exception thrown");

      // analyze
      assertEquals(result, false);
      assertEquals(keyboardFileDAO.keyboards.size(), testKeyboards.length);
  }

  @Test
  public void testUpdateKeyboardNotFound() {
      // setup
      Keyboard keyboard = new Keyboard(98, "Shoe", 4.99, 2);

      // invoke
      Keyboard result = assertDoesNotThrow(() -> keyboardFileDAO.update(keyboard),
                                              "Unexpected exception thrown");

      // analyze
      assertNull(result);
  }

  @Test
  public void testConstructorException() throws IOException {
      // setup
      ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
      
      // we want to simulate with a Mock Object Mapper that an
      // exception was raised during JSON object deseerialization
      // into Java objects
      // when the Mock Object Mapper readValue method is called
      // from the KeyboardFileDAO load method, an IOException is
      // raised
      doThrow(new IOException())
            .when(mockObjectMapper)
              .readValue(new File("doesnt_matter.txt"), Keyboard[].class);

      // invoke & analyze
      assertThrows(IOException.class,
                          () -> new KeyboardFileDAO("doesnt_matter.txt", mockObjectMapper),
                          "IOException not thrown");
  }
}
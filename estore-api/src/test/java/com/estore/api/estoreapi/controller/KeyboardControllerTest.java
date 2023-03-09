package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.KeyboardFileDAO;
import com.estore.api.estoreapi.model.Keyboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The unit test class for the Keyboard Controller class.
 * 
 * @author Cathy Liu (cl6606@rit.edu)
 */
@Tag("Controller-tier")
public class KeyboardControllerTest {
  private KeyboardController keyboardController;
  private KeyboardFileDAO mockKeyboardFileDAO;

  @BeforeEach
  public void setupKeyboardController() {
      mockKeyboardFileDAO = mock(KeyboardFileDAO.class);
      keyboardController = new KeyboardController(mockKeyboardFileDAO);
  }

  @Test
  public void testGetKeyboard() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(1, "GMMK PRO", 349.99, 300);

    // when the same id is passed in, our mock Generic DAO will return the Keyboard object
    when(mockKeyboardFileDAO.findById(keyboard.getId())).thenReturn(keyboard);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.getKeyboard(keyboard.getId());

    // analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(keyboard, response.getBody());
  }

  @Test
  public void testGetKeyboardNotFound() throws IOException {
    // setup
    int keyboardId = 1;

    // when the same id is passed in, our mock Generic DAO will return null, simulating
    // no keyboard found
    when(mockKeyboardFileDAO.findById(keyboardId)).thenReturn(null);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.getKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testGetKeyboardHandleException() throws Exception {
    // setup
    int keyboardId = 1;

    // when getKeyboard is called on the Mock Generic DAO, throw an IOException
    doThrow(new IOException()).when(mockKeyboardFileDAO).findById(keyboardId);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.getKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testCreateKeyboard() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Pear", 250, 10);

    // when createKeyboard is called, return true simulating successful creation and save
    when(mockKeyboardFileDAO.create(keyboard)).thenReturn(keyboard);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.createKeyboard(Keyboard);

    // analyze
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(keyboard, response.getBody());
  }

  @Test
  public void testCreateKeyboardFailed() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Apple", 250, 10);

    // when createKeyboard is called, return false simulating failed creation and save
    when(mockKeyboardFileDAO.create(keyboard)).thenReturn(null);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.createKeyboard(keyboard);

    // analyze
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
  }

  @Test
  public void testCreateKeyboardHandleException() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Banana", 100, 15);

    // when createKeyboard is called on the Mock Generic DAO, throw an IOException
    doThrow(new IOException()).when(mockKeyboardFileDAO).create(keyboard);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.createKeyboard(keyboard);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testUpdateKeyboard() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Pear", 250, 10);
    
    // when updateKeyboard is called, return true simulating successful update and save
    when(mockKeyboardFileDAO.update(keyboard)).thenReturn(keyboard);
    ResponseEntity<Keyboard> response = keyboardController.updateKeyboard(keyboard);
    keyboard.setName("Apple");

    // invoke
    response = keyboardController.updateKeyboard(keyboard);

    // analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(keyboard, response.getBody());
  }

  @Test
  public void testUpdateKeyboardFailed() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Berry", 250, 15);

    // when updateKeyboard is called, return true simulating successful update and save
    when(mockKeyboardFileDAO.update(keyboard)).thenReturn(null);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.updateKeyboard(keyboard);

    // analyze
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testUpdateKeyboardHandleException() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Berry", 250, 15);

    // when updateKeyboard is called on the Mock Keyboard DAO, throw an IOException
    doThrow(new IOException()).when(mockKeyboardFileDAO).update(keyboard);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.updateKeyboard(keyboard);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testGetKeyboards() throws IOException {
    // setup
    Keyboard[] keyboards = new Keyboard[2];
    keyboards[0] = new Keyboard(99, "Banana", 100, 15);
    keyboards[1] = new Keyboard(100, "Orange", 175, 10);

    // when getKeyboards is called return the keyboards created above
    when(mockKeyboardFileDAO.getKeyboards()).thenReturn(keyboards);

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.getKeyboards();

    // analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(keyboards, response.getBody());
  }

  @Test
  public void testGetKeyboardsHandleException() throws IOException {
    // setup
    // when getKeyboards is called on the Mock Generic DAO, throw an IOException
    doThrow(new IOException()).when(mockKeyboardFileDAO).getKeyboards();

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.getKeyboards();

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testSearchKeyboards() throws IOException {
    // setup
    String searchString = "an";
    Keyboard[] keyboards = new Keyboard[2];
    keyboards[0] = new Keyboard(99, "Banana", 100, 15);
    keyboards[1] = new Keyboard(100, "Orange", 175, 10);

    // when findKeyboards is called with the search string, return the two keyboards above
    when(mockKeyboardFileDAO.findByName(searchString)).thenReturn(keyboards);

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.searchKeyboards(searchString);

    // analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(keyboards, response.getBody());
  }

  @Test
  public void testSearchKeyboardsHandleException() throws IOException {
    // setup
    String searchString = "la";

    // when createKeyboard is called on the Mock Generic DAO, throw an IOException
    doThrow(new IOException()).when(mockKeyboardFileDAO).findByName(searchString);

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.searchKeyboards(searchString);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testDeleteKeyboard() throws IOException {
    // setup
    int keyboardId = 99;

    // when delete is called return true, simulating successful deletion
    when(mockKeyboardFileDAO.delete(keyboardId)).thenReturn(true);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.deleteKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeleteKeyboardNotFound() throws IOException {
    // setup
    int keyboardId = 99;

    // when deleteKeyboard is called return false, simulating failed deletion
    when(mockKeyboardFileDAO.delete(keyboard)).thenReturn(false);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.deleteKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testDeleteKeyboardHandleException() throws IOException {
    // setup
    int keyboardId = 99;

    // when deleteKeyboard is called on the Mock Generic DAO, throw an IOException
    doThrow(new IOException()).when(mockKeyboardFileDAO).delete(keyboardId);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.deleteKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }
}
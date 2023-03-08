package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.GenericDAO;
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
public class KeyboardControllerTest {
  private KeyboardController keyboardController;
  private GenericDAO mockGenericDAO;

  @BeforeEach
  public void setupKeyboardController() {
      mockGenericDAO = mock(GenericDAO.class);
      keyboardController = new KeyboardController(mockGenericDAO);
  }

  @Test
  public void testGetKeyboard() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(1, "GMMK PRO", 349.99, 300);

    // when the same id is passed in, our mock Generic DAO will return the Keyboard object
    when(mockGenericDAO.findById(keyboard.getId())).thenReturn(keyboard);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.getKeyboard(keyboard.getId());

    // analyze
    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertEquals(keyboard,response.getBody());
  }

  @Test
  public void testGetKeyboardNotFound() throws IOException {
    // setup
    int keyboardId = 1;

    // when the same id is passed in, our mock Generic DAO will return null, simulating
    // no keyboard found
    when(mockGenericDAO.findById(keyboardId)).thenReturn(null);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.getKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testGetKeyboardHandleException() throws Exception {
    // setup
    int keyboardId = 1;

    // when getHero is called on the Mock Hero DAO, throw an IOException
    doThrow(new IOException()).when(mockGenericDAO).findById(keyboardId);

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
    when(mockGenericDAO.create(keyboard)).thenReturn(keyboard);

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
    when(mockGenericDAO.create(keyboard)).thenReturn(null);

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
    doThrow(new IOException()).when(mockGenericDAO).create(keyboard);

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
    when(mockGenericDAO.update(keyboard)).thenReturn(keyboard);
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
    when(mockGenericDAO.updateKeyboard(keyboard)).thenReturn(null);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.updateKeyboard(keyboard);

    // analyze
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testUpdateKeyboardHandleException() throws IOException {
    // setup
    Keyboard keyboard = new Keyboard(99, "Berry", 250, 15);

    // when updateKeyboard is called on the Mock Hero DAO, throw an IOException
    doThrow(new IOException()).when(mockGenericDAO).update(keyboard);

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
    when(mockGenericDAO.getKeyboards()).thenReturn(keyboards);

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.getKeyboards();

    // analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(keyboards, response.getBody());
  }

  @Test
  public void testGetHeroesHandleException() throws IOException {
    // setup
    // when getKeyboards is called on the Mock Generic DAO, throw an IOException
    doThrow(new IOException()).when(mockGenericDAO).getHeroes();

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.getAll();

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
    when(mockGenericDAO.findByName(searchString)).thenReturn(keyboards);

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
    doThrow(new IOException()).when(mockGenericDAO).findByName(searchString);

    // invoke
    ResponseEntity<Keyboard[]> response = keyboardController.searchKeyboards(searchString);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testDeleteKeyboard() throws IOException {
    // setup
    int keyboardId = 99;
    // when deleteHero is called return true, simulating successful deletion
    when(mockGenericDAO.deleteKeyboard(keyboardId)).thenReturn(true);

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
    when(mockGenericDAO.deleteHero(heroId)).thenReturn(false);

    // invoke
    ResponseEntity<Keyboard> response = heroController.deleteHero(heroId);

    // analyze
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testDeleteKeyboardHandleException() throws IOException {
    // setup
    int keyboardId = 99;
    // when deleteHero is called on the Mock Hero DAO, throw an IOException
    doThrow(new IOException()).when(mockGenericDAO).deleteKeyboard(keyboardId);

    // invoke
    ResponseEntity<Keyboard> response = keyboardController.deleteKeyboard(keyboardId);

    // analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }
}
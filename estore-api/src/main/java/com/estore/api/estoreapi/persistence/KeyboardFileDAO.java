package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Keyboard;

/**
 * Implements the functionality for JSON file-based peristance for Keyboard
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Siddhartha Juluru (ssj4651@rit.edu)
 */
@Component
public class KeyboardFileDAO implements KeyboardDAO {
  @Override
  public Keyboard[] getAllKeyboards() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Keyboard[] findKeyboardsByName(String containsText) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Keyboard getKeyboardById(int id) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Keyboard createKeyboard(Keyboard keyboard) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Keyboard updateKeyboard(Keyboard keyboard) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteKeyboard(int id) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }
   
}

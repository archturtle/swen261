package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/** 
 * The unit test class for the User class 
 * 
 * @author Issac Kim (idk4565@rit.edu)
*/
public class UserTest {

    private final int expectedID = 1; //The ID for the user

    private final String expectedName = "Issac"; //The name for the user
    
    private final int expectedRole = 0; //The role for the user

    private final List<Keyboard> expectedCart = new ArrayList<Keyboard>(); //The cart for the user

    private final String expectedToString = "User [id=1, name=Issac, role=0, cart=[[]]"; //The expected toString for the user

    private final int keyboardID = 1; //The expected ID for the keyboard

    private final String keyboardName = "GMMK PRO"; //The expected name for the keyboard

    private final double keyboardPrice = 349.99; //The expected price for the keyboard

    private final int keyboardQuantity = 300; //The expected quantity for the keyboard

    private final String combinedToString = "User [id=1, name=Issac, role=0, cart=[[Keyboard [id=2, name=GMMK PRO, price=349.990000, quantity=300]]]";
        //The expected toString for the user after adding to cart

    @Test
    public void testConstruction(){
        //Tests if the user object is constructed correctly
        User newUser = new User(expectedID, expectedName, expectedRole);

        assertEquals(newUser.getId(), expectedID);
        assertEquals(newUser.getName(), expectedName);
        assertEquals(newUser.getRole(), expectedRole);
    }

    @Test
    public void testSetName(){
        //Tests if the user name is set properly
        User newUser = new User(expectedID, expectedName, expectedRole);

        String newName = "Jennie";

        newUser.setName(newName);
        assertEquals(newUser.getName(), newName);
    }

    @Test
    public void testSetRole(){
        //Tests if the user role is set properly
        User newUser = new User(expectedID, expectedName, expectedRole);

        int newRole = 1;
        newUser.setRole(newRole);
        assertEquals(newUser.getRole(), newRole);
    }

    @Test
    public void testAddToCart(){
        //Tests if the card was added to properly
        Keyboard newKeyboard = new Keyboard(keyboardID, keyboardName, keyboardPrice, keyboardQuantity);
        User newUser = new User(expectedID, expectedName, expectedRole);

        newUser.addToCart(newKeyboard);
        assertEquals(newUser.toString(), combinedToString);
    }

    @Test
    public void testRemoveFromCart(){
        //Tests if the card was removed from properly
        Keyboard newKeyboard = new Keyboard(keyboardID, keyboardName, keyboardPrice, keyboardQuantity);
        User newUser = new User(expectedID, expectedName, expectedRole);

        newUser.addToCart(newKeyboard);
        assertEquals(newUser.toString(), combinedToString);
        
        newUser.removeFromCart(newKeyboard);
        assertEquals(newUser.toString(), expectedToString);
    }
}

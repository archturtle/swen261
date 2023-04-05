package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

/** 
 * The unit test class for the User class 
 * 
 * @author Issac Kim (idk4565@rit.edu)
*/

@Tag("Model-tier")
class UserTest {

    private final int expectedID = 1; //The ID for the user

    private final String expectedName = "Issac"; //The name for the user

    private final int expectedRole = 0; //The role for the user

    private final String expectedRemovedString = "User [id=1, name=Issac, role=0, cart=[\n]]";

    private final int keyboardID = 1; //The expected ID for the keyboard

    private final String keyboardName = "GMMK PRO"; //The expected name for the keyboard

    private final double keyboardPrice = 349.99; //The expected price for the keyboard

    private final String keyboardDescription = "It's a keyboard"; //The expected description for the keyboard

    private final int keyboardQuantity = 300; //The expected quantity for the keyboard

    //The expected toString for the user after adding to cart
    private final String combinedToString = "User [id=1, name=Issac, role=0, cart=[\n\t1\n]]";

    private final String expectedCartString = "[1]";

    @Test
    void testConstruction(){
        //Tests if the user object is constructed correctly
        User newUser = new User(expectedID, expectedName, expectedRole);

        assertEquals(newUser.getId(), expectedID);
        assertEquals(newUser.getName(), expectedName);
        assertEquals(newUser.getRole(), expectedRole);
    }

    @Test
    void testSetName(){
        //Tests if the user name is set properly
        User newUser = new User(expectedID, expectedName, expectedRole);

        String newName = "Jennie";

        newUser.setName(newName);
        assertEquals(newUser.getName(), newName);
    }

    @Test
    void testSetRole(){
        //Tests if the user role is set properly
        User newUser = new User(expectedID, expectedName, expectedRole);

        int newRole = 1;
        newUser.setRole(newRole);
        assertEquals(newUser.getRole(), newRole);
    }

    @Test
    void testAddToCart(){
        //Tests if the card was added to properly
        Keyboard newKeyboard = new Keyboard(keyboardID, keyboardName, keyboardPrice, keyboardDescription, keyboardQuantity);
        User newUser = new User(expectedID, expectedName, expectedRole);

        newUser.addToCart(newKeyboard.getId());
        assertEquals(newUser.toString(), combinedToString);
    }

    @Test void testGetCart() {
        // Checks if getting the cart works
        Keyboard newKeyboard = new Keyboard(keyboardID, keyboardName, keyboardPrice, keyboardDescription, keyboardQuantity);
        User newUser = new User(expectedID, expectedName, expectedRole);

        newUser.addToCart(newKeyboard.getId());
        assertEquals(expectedCartString, newUser.getCart().toString());
    }

    @Test
    void testRemoveFromCart(){
        //Tests if the card was removed from properly
        Keyboard newKeyboard = new Keyboard(keyboardID, keyboardName, keyboardPrice, keyboardDescription, keyboardQuantity);
        User newUser = new User(expectedID, expectedName, expectedRole);

        newUser.addToCart(newKeyboard.getId());
        assertEquals(newUser.toString(), combinedToString);
        
        newUser.removeFromCart(newKeyboard.getId());
        assertEquals(newUser.toString(), expectedRemovedString);
    }
}

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

    private final int expectedID = 1;

    private final String expectedName = "Issac";
    
    private final int expectedRole = 0;

    private final List<Keyboard> expectedCart = new ArrayList<Keyboard>();

    private final String expectedToString = "User [id=1, name=Issac, role=0, cart=[]]";

    private final int keyboardID = 1;

    private final String keyboardName = "GMMK PRO";

    private final double keyboardPrice = 349.99;

    private final int keyboardQuantity = 300;
    
    private final String keyboardToString = "Keyboard [id=2, name=GMMK PRO, price=349.990000, quantity=300]";

    private final Keyboard newKeyboard = new Keyboard(keyboardID, keyboardName, keyboardPrice, keyboardQuantity);

    @Test
    public void testConstruction(){
        User newUser = new User(expectedID, expectedName, expectedRole);

        assertEquals(newUser.getId(), expectedID);
        assertEquals(newUser.getName(), expectedName);
        assertEquals(newUser.getRole(), expectedRole);
    }

    @Test
    public void testSetName(){
        User newUser = new User(expectedID, expectedName, expectedRole);

        String newName = "Jennie";

        newUser.setName(newName);
        assertEquals(newUser.getName(), newName);
    }

    @Test
    public void testSetRole(){
        User newUser = new User(expectedID, expectedName, expectedRole);

        int newRole = 1;
        newUser.setRole(newRole);
        assertEquals(newUser.getRole(), newRole);
    }

    @Test
    public void testAddToCart(){

    }
}

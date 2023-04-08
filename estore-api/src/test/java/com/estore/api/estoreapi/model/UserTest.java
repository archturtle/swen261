package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.CartItem.Type;

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

    private final int keyboardID = 1; //The expected ID for the keyboard

    //The expected toString for the user after adding to cart
    private final String expectedToString = "User [id=1, name=Issac, role=0, cart=[\n]]";

    private final String expectedToStringWithCart = "User [id=1, name=Issac, role=0, cart=[\n\tCartItem [cartItemType=STANDARD_KEYBOARD, quantity=1, keyboardID=1, customKeyboard=null]\n]]";

    private final String expectedCartString = "[CartItem [cartItemType=STANDARD_KEYBOARD, quantity=1, keyboardID=1, customKeyboard=null]]";

    @Test
    void testConstruction(){
        //Tests if the user object is constructed correctly
        User newUser = new User(expectedID, expectedName, expectedRole, List.of());

        assertEquals(newUser.getId(), expectedID);
        assertEquals(newUser.getName(), expectedName);
        assertEquals(newUser.getRole(), expectedRole);
        assertEquals(newUser.getCart(), List.of());
    }

    @Test
    void testSetName(){
        //Tests if the user name is set properly
        User newUser = new User(expectedID, expectedName, expectedRole, List.of());

        String newName = "Jennie";

        newUser.setName(newName);
        assertEquals(newUser.getName(), newName);
    }

    @Test
    void testSetRole(){
        //Tests if the user role is set properly
        User newUser = new User(expectedID, expectedName, expectedRole, List.of());

        int newRole = 1;
        newUser.setRole(newRole);
        assertEquals(newUser.getRole(), newRole);
    }

    @Test void testSetCart() {
        // Checks if getting the cart works
        User newUser = new User(expectedID, expectedName, expectedRole, List.of());
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboardID, null);
        newUser.setCart(List.of(cartItem));

        assertEquals(expectedCartString, newUser.getCart().toString());
    }

    @Test void testToString() {
        User newUser = new User(expectedID, expectedName, expectedRole, List.of());
        assertEquals(expectedToString, newUser.toString());
    }

    @Test void testToStringWithCart() {
        User newUser = new User(expectedID, expectedName, expectedRole, List.of());
        CartItem cartItem = new CartItem(Type.STANDARD_KEYBOARD, 1, keyboardID, null);
        newUser.setCart(List.of(cartItem));

        assertEquals(expectedToStringWithCart, newUser.toString());
    }
}

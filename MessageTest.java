/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuickChat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author itumelengteffo
 */
public class MessageTest {
    

 @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Creating a string longer than 250 characters
        String longMessage = "a".repeat(251); 
        Message msg = new Message("+27718693002", longMessage, 1);
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        // Missing international code and too long
        Message msg = new Message("08575975889", "Test", 1); 
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCreation() {
        // Test data from image 9: Message 1
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        // Expected format: First two of ID (random) + :0: + First & Last words
        // Since ID is random, we can only reliably test the suffix part in a real environment. 
        // However, based on the requirement example:
        String expectedEnd = ":0:HITONIGHT"; 
        assertTrue(msg.getMessageHash().endsWith(expectedEnd));
    }
}
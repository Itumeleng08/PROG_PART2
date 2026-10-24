/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuickChat;

import java.util.Random;

/**
 *
 * @author itumelengteffo
 */
public final class Message {
    
private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.length() <= 12 && recipient.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "";
  }
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return (idPrefix + ":" + messageNumber + ":" + (firstWord + lastWord)).toUpperCase();
    }

    // FIXED: small 's' and added periods to match your test
    public String sentMessage(int choice) {
    return switch (choice) {
        case 1 -> "Message successfully sent.";
        case 2 -> "Press 0 to delete the message.";
        case 3 -> "Message successfully stored.";
        default -> "Invalid choice";
    };
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    public String storeMessage() {
        return "{\n" +
               "  \"messageID\": \"" + messageID + "\",\n" +
               "  \"messageHash\": \"" + messageHash + "\",\n" +
               "  \"recipient\": \"" + recipient + "\",\n" +
               "  \"message\": \"" + messageText + "\"\n" +
               "}";
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}

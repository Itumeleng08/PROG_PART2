/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuickChat;

import com.mycompany.quickchatapp.Message;
import java.util.Scanner;



/**
 *
 * @author itumelengteffo
 */
public class QuickChat {
    
     public static void main(String[] args) {
         // Requirement: Users should only send messages if logged in successfully
         try (Scanner scanner = new Scanner(System.in)) {
             // Requirement: Users should only send messages if logged in successfully
             boolean isLoggedIn = false;
             while (!isLoggedIn) {
                 System.out.print("Enter username: ");
                 String user = scanner.nextLine();
                 System.out.print("Enter password: ");
                 String pass = scanner.nextLine();
                 
                 // Dummy login logic for the sake of this task
                 if (user.equals("admin") && pass.equals("password")) {
                     isLoggedIn = true;
                     System.out.println("Login successful.");
                 } else {
                     System.out.println("Invalid credentials, try again.");
                 }
             }
             
             // Requirement: Welcome message
             System.out.println("\nWelcome to QuickChat.");
             
             // Requirement: Define how many messages to enter
             System.out.print("How many messages do you wish to enter? ");
             int numMessages = scanner.nextInt();
             scanner.nextLine(); // Consume newline
             
             boolean running = true;
             
             while (running) {
                 System.out.println("\n--- Main Menu ---");
                 System.out.println("Option 1) Send Messages");
                 System.out.println("Option 2) Show recently sent messages");
                 System.out.println("Option 3) Quit");
                 System.out.print("Choose an option: ");
                 
                 int choice = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
                 
                 switch (choice) {
                     case 1 -> processMessages(scanner, numMessages);
                     case 2 -> System.out.println("Coming Soon.");
                     case 3 -> {
                         System.out.println("Exiting application.");
                         running = false;
                     }
                     default -> System.out.println("Invalid choice. Please select 1, 2, or 3.");
                 }
             }
         }
    }

    private static void processMessages(Scanner scanner, int numMessages) {
        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
            
            System.out.print("Enter recipient cell number (e.g., +2779593002): ");
            String recipient = scanner.nextLine();

            System.out.print("Enter message (max 250 characters): ");
            String text = scanner.nextLine();

            // Requirement: Message length check
            if (text.length() > 250) {
                int excess = text.length() - 250;
                System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                i--; // Decrement i so they have to try this message again
                continue;
            } else {
                System.out.println("Message ready to send.");
            }

            // Create Message Object
            Message msg = new Message(recipient, text);

            // Check formatting (Optional but recommended based on rubric)
            System.out.println(msg.checkRecipientCell());

            // Requirement: Ask user to choose options after completing message
            System.out.println("\nChoose an action:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Choice: ");
            scanner.nextLine(); // Consume newline
            int action = 0;
            

  String result = msg.sentMessage(action);
            System.out.println(result);

            // Requirement: Display full details after sent
            if (action == 1) {
                System.out.println("\n--- Message Details ---");
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());
            }
        }

        // Requirement: Display total number of messages sent
        System.out.println("\nTotal messages successfully sent: " + Message.returnTotalMessages());
        
        // Display all sent messages (calling the printMessages method)
        if (Message.returnTotalMessages() > 0) {
            System.out.println("\n--- All Sent Messages ---");
            
            System.out.println(Message.printMessages());
        }
    }
}

            
    
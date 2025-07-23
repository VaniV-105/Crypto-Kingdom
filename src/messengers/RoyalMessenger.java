package messengers;

import scrolls.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RoyalMessenger {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket throneGate = new Socket(host, port);
             ObjectOutputStream scrollWriter = new ObjectOutputStream(throneGate.getOutputStream());
             ObjectInputStream scrollReader = new ObjectInputStream(throneGate.getInputStream());
             Scanner quill = new Scanner(System.in)) {

            // Ask for input
            System.out.println("🖊️ Enter your message for the King:");
            String data = quill.nextLine();

            System.out.println("⚔️ Choose operation [ENCRYPT/DECRYPT]:");
            ScrollType operation = ScrollType.valueOf(quill.nextLine().toUpperCase());

            System.out.println("🔐 Choose cipher [AES/DES/RSA/CAESAR/VIGENERE]:");
            CipherType algorithm = CipherType.valueOf(quill.nextLine().toUpperCase());

            System.out.println("🗝️ Enter the key (or shift/keyword):");
            String key = quill.nextLine();

            RoyalScroll scroll = new RoyalScroll(operation, algorithm, data, key);
            scrollWriter.writeObject(scroll);
            scrollWriter.flush();

            RoyalReply reply = (RoyalReply) scrollReader.readObject();
            if (reply.isSuccess()) {
                System.out.println("👑 The King's response: " + reply.getResult());
            } else {
                System.out.println("💥 The King failed to process your request: " + reply.getErrorMessage());
            }

        } catch (Exception e) {
            System.err.println("⚠️ Failed to send scroll to the throne: " + e.getMessage());
        }
    }
}

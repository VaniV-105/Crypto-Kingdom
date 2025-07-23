package throne;

import scrolls.*;
import java.io.*;
import java.net.Socket;

public class Herald implements Runnable {
    private final Socket messengerSocket;

    public Herald(Socket messengerSocket) {
        this.messengerSocket = messengerSocket;
    }

    @Override
    public void run() {
        try (
            ObjectInputStream scrollReader = new ObjectInputStream(messengerSocket.getInputStream());
            ObjectOutputStream scrollWriter = new ObjectOutputStream(messengerSocket.getOutputStream())
        ) {
            RoyalScroll scroll = (RoyalScroll) scrollReader.readObject();
            System.out.println("📜 Received scroll from RoyalMessenger: " + scroll.getAlgorithm());

            String result = null;
            boolean success = true;
            String error = null;

            try {
                result = RoyalCipherBook.handle(scroll); // This method decides which algo + op
            } catch (Exception e) {
                success = false;
                error = "The royal scribe stumbled: " + e.getMessage();
                e.printStackTrace();
            }

            RoyalReply reply = new RoyalReply(result, success, error);
            scrollWriter.writeObject(reply);
            scrollWriter.flush();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("⚠️ The Herald failed to deliver the scroll: " + e.getMessage());
        }
    }
}

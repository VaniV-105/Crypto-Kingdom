package throne;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KingThrone {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket royalGate = new ServerSocket(port)) {
            System.out.println("👑 The King is now listening on port " + port + "...");

            while (true) {
                Socket messenger = royalGate.accept();
                System.out.println("📨 A new RoyalMessenger has arrived.");
                new Thread(new Herald(messenger)).start();
            }
        } catch (IOException e) {
            System.err.println("⚠️ The King encountered an error: " + e.getMessage());
        }
    }
}

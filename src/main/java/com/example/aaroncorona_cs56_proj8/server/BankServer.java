package com.example.aaroncorona_cs56_proj8.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server that listens for client connections
public final class BankServer {

    private ServerSocket serverSocket;

    // Private constructor to reinforce a model of just 1 Server with 1+ threads and proxies
    private BankServer() {
        startServer();
    }

    // Launches the Server and waits for connections
    private void startServer() {
        try {
            // Create a server socket
            serverSocket = new ServerSocket(8000);

            // Continually listen for new socket connections
            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();
                // Create a thread handler object for Listening to the Client's request
                BankServerSocketHandler clientHandler = new BankServerSocketHandler(socket);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Main method to launch the Server
    public static void main(String[] args) {
        // Launch GUI
        BankServerGUI gui = new BankServerGUI();
        Thread guiThread = new Thread(gui);
        guiThread.start();
        // Start Server
        new BankServer();
    }
}

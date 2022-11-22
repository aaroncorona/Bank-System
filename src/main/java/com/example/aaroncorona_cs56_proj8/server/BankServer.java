package com.example.aaroncorona_cs56_proj8.server;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server that performs the Bank actions
public final class BankServer extends Application {

    private ServerSocket serverSocket;

    // Private constructor to force the use of inner Thread objects
    private BankServer() {
        startServer();
        launch();
    }

    // Helper method to launch the Server and wait for connections
    private void startServer() {
        try {
            // Create a server socket
            serverSocket = new ServerSocket(8001);
            System.out.println("Server started ");

            // Continually listen for new socket connections
            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

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
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void start(Stage stage) {

    }

    // Main method to launch the server
    public static void main(String[] args) {
        new BankServer();
    }
}

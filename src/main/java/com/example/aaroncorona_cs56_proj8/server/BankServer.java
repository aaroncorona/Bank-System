package com.example.aaroncorona_cs56_proj8.server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server that performs the Bank actions
public final class BankServer extends Application {

    private static ServerSocket serverSocket;

    private static Label labelStatus;

    // Private constructor to reinforce the design of just 1 Server with 1+ threads and proxies
    private BankServer() {}

    // Helper method to launch the Server and wait for connections
    private void startServer() {
        try {
            // Create a server socket
            serverSocket = new ServerSocket(8000);
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
                System.out.println(e);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        // Add a title Label
        final Label title = new Label("Server Responses");

        // Add a Label that shows the status of the Server response
        labelStatus = new Label();

        // Create an outer container
        final VBox vbox = new VBox();
        vbox.getChildren().addAll(labelStatus);

        // Wrap everything in a scrollbar
        final ScrollPane root = new ScrollPane();
        root.setFitToWidth(true);
        root.setContent(vbox);

        // Scene
        final Scene scene = new Scene(root, 400,500);
        stage.setTitle("Server Responses");
        stage.setScene(scene);
        stage.show();
    }

    // Allow the socket handler to update the response label to show the status of Server requests
    protected static void addStatusText(String text) {
        // TODO
    }

    // Main method to launch the server
    public static void main(String[] args) {
        launch(args);
    }
}

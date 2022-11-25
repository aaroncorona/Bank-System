package com.example.aaroncorona_cs56_proj8.server;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// GUI for the Server responses
public final class BankServerGUI extends Application implements Runnable {

    // Label where all Server status updates are logged
    private static Label labelStatus;
    private static String labelStatusText;

    @Override
    public void start(Stage stage) {

        // Add a title Label
        final Label title = new Label("Server Responses: ");

        // Add a Label that shows the status of the Server response
        labelStatus = new Label();
        labelStatusText = "";
        labelStatus.setText(labelStatusText);

        // Create a container for the labels
        final VBox vbox = new VBox();
        vbox.getChildren().addAll(title, labelStatus);
        vbox.setAlignment(Pos.TOP_CENTER);

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

    // Allow the socket handler threads to update the label to show the status of Server requests
    protected synchronized static void addStatusText(String newText) {
        labelStatusText += newText + "\n";
        labelStatus.setText(labelStatusText);
    }

    @Override
    public void run() {
        launch();
    }
}

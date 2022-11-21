/***************************************************************************
 Author: Aaron Corona
 Date: Nov 21st, 2022
 CS56 Project #8 - Bank System
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BankClient extends Application {

    // Component settings
    public static final int COMPONENT_WIDTH = 200;
    public static final int COMPONENT_HEIGHT = COMPONENT_WIDTH;

    @Override
    public void start(Stage primaryStage) {

        // Add a Label and Text fields for "Account Number" in a Hbox
        final Label labelAcctNum = new Label("Enter Account Number:  ");
        final TextField tfAcctNum = new TextField();
        tfAcctNum.setPromptText("Account Num");
        tfAcctNum.setPrefColumnCount(10);
        final HBox acctNumRow = new HBox();
        acctNumRow.setPadding(new Insets(15, 15, 15, 15));
        acctNumRow.setAlignment(Pos.CENTER);
        acctNumRow.getChildren().addAll(labelAcctNum, tfAcctNum);

        // Add a Label and Text field for "Amount" in a Hbox
        final Label labelAmt = new Label("Enter Amount (USD):  ");
        final TextField tfAmt = new TextField();
        tfAmt.setPromptText("Amount ($)");
        tfAmt.setPrefColumnCount(10);
        final HBox amtRow = new HBox();
        amtRow.setPadding(new Insets(5, 15, 15, 15));
        amtRow.setAlignment(Pos.CENTER);
        amtRow.getChildren().addAll(labelAmt, tfAmt);

        // Inject a Bank proxy TODO

        // Add Buttons for bank actions in an HBox container
        Button btnBalance = new Button("Balance");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnQuit = new Button("Quit");
        HBox buttonRow = new HBox();
        buttonRow.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10);
        buttonRow.getChildren().addAll(btnBalance, btnDeposit, btnWithdraw, btnQuit);

        // Set button actions to call the Bank proxy TODO
        btnBalance.setOnAction(event -> {
            // Return balance
        });
        btnDeposit.setOnAction(event -> {
            // Make deposit
        });
        btnWithdraw.setOnAction(event -> {
            // Make withdraw
        });
        btnQuit.setOnAction(event -> {
            // Close the connection
            System.exit(0);
        });

        // Add a text area that returns a message from the Bank proxy
        TextArea response = new TextArea();

        // Create a container (vertical box) for the rows of horizontal boxes
        VBox root = new VBox();
        root.getChildren().addAll(acctNumRow, amtRow, buttonRow, response);

        // Scene
        Scene scene = new Scene(root, 400,500);
        primaryStage.setTitle("Your Account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main method to launch app
    public static void main(String[] args) {
        launch();
    }
}
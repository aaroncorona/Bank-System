/***************************************************************************
 Author: Aaron Corona
 Date: Nov 21st, 2022
 CS56 Project #8 - Bank System
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj8.client;

import com.example.aaroncorona_cs56_proj8.proxy.Bank;
import com.example.aaroncorona_cs56_proj8.proxy.BankProxy;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public final class BankClient extends Application {

    private Bank bank;
    private TextField tfAcctNum;
    private TextField tfAmount;
    private String responseMsg = "";
    private Label labelStatus;

    @Override
    public void start(Stage stage) {

        // Create bank proxy
        bank = new BankProxy();

        // Add a Label and Text fields for "Account Number" in a Hbox
        final Label labelAcctNum = new Label("Enter Account Number:  ");
        tfAcctNum = new TextField();
        tfAcctNum.setPromptText("Account Num");
        tfAcctNum.setPrefColumnCount(10);
        final HBox acctNumRow = new HBox();
        acctNumRow.setPadding(new Insets(15, 15, 15, 15));
        acctNumRow.setAlignment(Pos.CENTER);
        acctNumRow.getChildren().addAll(labelAcctNum, tfAcctNum);

        // Add a Label and Text field for "Amount" in a Hbox
        final Label labelAmt = new Label("Enter Amount (USD):  ");
        tfAmount = new TextField();
        tfAmount.setPromptText("Amount ($)");
        tfAmount.setPrefColumnCount(10);
        final HBox amtRow = new HBox();
        amtRow.setPadding(new Insets(5, 15, 15, 15));
        amtRow.setAlignment(Pos.CENTER);
        amtRow.getChildren().addAll(labelAmt, tfAmount);

        // Add Buttons for bank actions in an HBox container
        final Button btnBalance = new Button("Balance");
        final Button btnDeposit = new Button("Deposit");
        final Button btnWithdraw = new Button("Withdraw");
        final Button btnQuit = new Button("Quit");
        final HBox buttonRow = new HBox();
        buttonRow.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10);
        buttonRow.getChildren().addAll(btnBalance, btnDeposit, btnWithdraw, btnQuit);

        // Add a Label that returns a message from the Bank proxy
        labelStatus = new Label();
        labelStatus.setText(responseMsg);

        // Set button actions to call the Bank proxy
        btnBalance.setOnAction(event -> {
            // Return balance and print result on Label
            addStatusText(bank.getBalance(getAcctNum()));
        });
        btnDeposit.setOnAction(event -> {
            // Make deposit and print result on Label
            addStatusText(bank.makeDeposit(getAcctNum(), getAmount()));
        });
        btnWithdraw.setOnAction(event -> {
            // Make withdraw and print result on Label
            addStatusText(bank.makeWithdraw(getAcctNum(), getAmount()));
        });
        btnQuit.setOnAction(event -> {
            // Close the connection and exit the program
            bank.endConnection();
            System.exit(0);
        });

        // Create an outer container (vertical box) for the rows
        final VBox vbox = new VBox();
        vbox.getChildren().addAll(acctNumRow, amtRow, buttonRow, labelStatus);

        // Wrap everything in a scrollbar
        final ScrollPane root = new ScrollPane();
        root.setFitToWidth(true);
        root.setContent(vbox);

        // Scene
        final Scene scene = new Scene(root, 400,500);
        stage.setTitle("Your Account");
        stage.setScene(scene);
        stage.show();
    }

    // Helper method to get the account number
    private int getAcctNum() {
        int acctNum = 0;
        try {
            acctNum = Integer.parseInt(tfAcctNum.getText());
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return acctNum;
    }

    // Helper method to get the amount
    private int getAmount() {
        int amount = 0;
        try {
            amount = Integer.parseInt(tfAmount.getText());
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return amount;
    }

    // Helper method to update the response label
    private void addStatusText(String newMsg) {
        responseMsg += newMsg + "\n";
        labelStatus.setText(responseMsg);
    }

    // Main method to launch app
    public static void main(String[] args) {
        launch(args);
    }
}
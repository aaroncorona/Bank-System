package com.example.aaroncorona_cs56_proj8.proxy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

// This is a user-facing Proxy that connects to the real Bank Server
public final class BankProxy implements Bank {

    private Socket socket;
    private ObjectOutputStream toServer;
    private DataInputStream fromServer;

    public BankProxy() {
        createBankClientSocket();
    }

    // Helper method to create a new socket with the Server
    private void createBankClientSocket() {
        try {
            // Create a socket along with output & input streams to the server
            socket = new Socket("localhost", 8000);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBalance(int acctNum) {
        // Request balance if valid request
        if(acctNum > 0) {
            try {
                // Send request
                System.out.println("Sending request...");
                BankServerRequest request = new BankServerRequest("Balance", acctNum);
                toServer.writeObject(request);
                // Return response
                System.out.println("Getting response...");
                String response = fromServer.readUTF();
                return response;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return "Error - Invalid Account Number";
        }
    }

    @Override
    public String makeDeposit(int acctNum, int amount) {
        // Make deposit if valid request
        if(acctNum > 0 && amount > 0) {
            try {
                // Send request
                BankServerRequest request = new BankServerRequest("Deposit", acctNum, amount);
                toServer.writeObject(request);
                // Return response
                String response = fromServer.readUTF();
                return response;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return "Error - Invalid Account Number or Invalid Amount";
        }
    }

    @Override
    public String makeWithdraw(int acctNum, int amount) {
        // TODO Check if there are enough funds for the withdrawal on behalf of the Server. Otherwise, make the withdrawal
        // Make withdrawal if valid request
        if(acctNum > 0 && amount > 0) {
            try {
                // Send request
                BankServerRequest request = new BankServerRequest("Withdraw", acctNum, amount);
                toServer.writeObject(request);
                // Return response
                String response = fromServer.readUTF();
                return response;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return "Error - Invalid Account Number or Invalid Amount";
        }
    }

    @Override
    public String endConnection() {
        try {
            socket.close();
            socket = null;
            return "Connection closed";
        } catch (IOException e) {
            System.out.println(e);
        }
        socket = null;
        return "Connection closed";
    }
}

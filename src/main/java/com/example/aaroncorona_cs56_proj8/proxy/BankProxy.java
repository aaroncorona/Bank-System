package com.example.aaroncorona_cs56_proj8.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// This is a user-facing Proxy that connects to the real Bank Server
public final class BankProxy implements Bank {

    private Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    public BankProxy() {
        createBankClientSocketStreams();
    }

    // Helper method to create a new socket and stream with the Server
    private void createBankClientSocketStreams() {
        try {
            // Create a socket along with output & input streams to the server
            socket = new Socket("localhost", 8000);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public String getBalance(int acctNum) {
        // Request balance if valid request
        if(acctNum > 0) {
            try {
                // Send request to Server
                BankServerRequest request = new BankServerRequest("Balance", acctNum);
                toServer.writeObject(request);
                // Return response from Server
                String response = fromServer.readObject().toString();
                return response;
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        return "Error - Invalid Account Number";
    }

    @Override
    public String makeDeposit(int acctNum, int amount) {
        // Request deposit if valid request
        if(acctNum > 0 && amount > 0) {
            try {
                // Send request
                BankServerRequest request = new BankServerRequest("Deposit", acctNum, amount);
                toServer.writeObject(request);
                // Return response
                String response = fromServer.readObject().toString();
                return response;
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        return "Error - Invalid Account Number or Invalid Amount";
    }

    @Override
    public String makeWithdraw(int acctNum, int amount) {
        // Request withdrawal if valid request
        if(acctNum > 0 && amount > 0) {
            try {
                // Send request
                BankServerRequest request = new BankServerRequest("Withdraw", acctNum, amount);
                toServer.writeObject(request);
                // Return response
                String response = fromServer.readObject().toString();
                return response;
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        }
        return "Error - Invalid Account Number or Invalid Amount";
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

package com.example.aaroncorona_cs56_proj8.server;

import java.io.DataInputStream;
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
        // TODO add socket connection here
    }

    @Override
    public String getBalance(int acctNum) {
        // Request balance if valid request
        if(acctNum > 0) {
            // TODO send output stream
            // TODO get input stream
            //            fromServer = new DataInputStream(socket.getInputStream());
        }
        return null; // TODO send error mes
    }

    @Override
    public String makeDeposit(int acctNum, int amount) {
        // Make deposit if valid request
        if(acctNum > 0 && amount > 0) {
            // TODO send output stream
            // TODO get input stream
        }
        return null; // TODO send error mes
    }

    @Override
    public String makeWithdraw(int acctNum, int amount) {
        // Check if there are enough funds for the withdrawal on behalf of the Server. Otherwise, make the withdrawal
//        if(this.getBalance(acctNum) >= amount
//                && acctNum > 0) {
            // TODO send output stream
            // TODO get input stream
//        }
        return null; // TODO send error mes
    }

    @Override
    public String endConnection() {
         // TODO Close the client thread
        return "Connection closed";
    }
}

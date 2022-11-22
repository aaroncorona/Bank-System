package com.example.aaroncorona_cs56_proj8.server;

import com.example.aaroncorona_cs56_proj8.proxy.Bank;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

// Helper class to handle individual Socket connections
public final class BankServerSocketHandler implements Bank, Runnable {
    private Socket socket;
    private HashMap<Integer, Integer> accountBalance;

    private ObjectInputStream inputFromClient;
    private DataOutputStream outputToClient;


    protected BankServerSocketHandler(Socket socket) {
        this.socket = socket;

        accountBalance = new HashMap<>();
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            // Get the client request object, then send a response String
            try {
                inputFromClient = new ObjectInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
                // Parse request
                Object object = inputFromClient.readObject();
                System.out.println(object);
                // TODO Perform requested action
                // TODO Send response
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBalance(int acctNum) {
        // TODO
        return null;
    }

    @Override
    public String makeDeposit(int acctNum, int amount) {
        // TODO
        return null;
    }

    @Override
    public String makeWithdraw(int acctNum, int amount) {
        // TODO
        return null;
    }

    @Override
    public String endConnection() {
        // TODO
        return null;
    }
}

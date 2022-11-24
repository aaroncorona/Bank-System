package com.example.aaroncorona_cs56_proj8.server;

import com.example.aaroncorona_cs56_proj8.proxy.Bank;
import com.example.aaroncorona_cs56_proj8.proxy.BankServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

// Helper class to handle individual Socket connections
public final class BankServerSocketHandler implements Bank, Runnable {
    private Socket socket;

    // All threads should reference the same account map
    private static HashMap<Integer, Integer> accountBalance;

    protected BankServerSocketHandler(Socket socket) {
        this.socket = socket;

        accountBalance = new HashMap<>();
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            // Get the client request object, then send a response String
            try {
                ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
                // TODO Parse request
                Object object = fromClient.readObject();
                // TODO Perform requested action
                if(object.toString().contains("Balance")) {
//                    getBalance();
                }
                // TODO Send response to Client
                BankServerResponse response = new BankServerResponse("Success", "Balance", 8888, 100);
                toClient.writeObject(response);
                // TODO update Server GUI label
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBalance(int acctNum) {
        // TODO write logic
        // TODO make synchronized and delete map so it's thread safe
        return null;
    }

    @Override
    public String makeDeposit(int acctNum, int amount) {
        // TODO write logic
        // TODO make synchronized and delete map so it's thread safe
        return null;
    }

    @Override
    public String makeWithdraw(int acctNum, int amount) {
        // TODO write logic
        // TODO make synchronized and delete map so it's thread safe
        return null;
    }

    @Override
    public String endConnection() {
        // TODO write logic
        return null;
    }

    // Helper method to rebuild the hashmap in a new memory slot for better thread safety
    private synchronized void updateMap(int key, int value) {
        // TODO write logic
    }
}

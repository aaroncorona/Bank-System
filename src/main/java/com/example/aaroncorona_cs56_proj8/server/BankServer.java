package com.example.aaroncorona_cs56_proj8.server;

import java.util.HashMap;

// Server that performs the Bank actions
public final class BankServer implements Bank {
    private HashMap<Integer, Integer> accountBalance = new HashMap<>();

    // Private constructor to force the use of inner Thread objects
    private BankServer() {
        launchServer();
    }

    // Helper method to launch the Server and wait for connections
    private void launchServer() {
        // TODO add socket listener
    }

    // Inner class to handle socket connections
    private static class BankServerSocket implements Runnable {
        // TODO implement Runnable
        private BankServerSocket() {

        }

        @Override
        public void run() {

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

    // Main method to launch the server
    public static void main(String[] args) {
        new BankServer();
        // GUI TODO
    }
}

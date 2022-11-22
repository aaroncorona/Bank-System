package com.example.aaroncorona_cs56_proj8.server;

import com.example.aaroncorona_cs56_proj8.proxy.Bank;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

// Helper class to handle individual Socket connections
public final class BankServerSocketHandler implements Bank, Runnable {
    private Socket socket;
    private HashMap<Integer, Integer> accountBalance;

    protected BankServerSocketHandler(Socket socket) {
        this.socket = socket;

        accountBalance = new HashMap<>();
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            // Get the next client message (structure is request type, account ID, amount)
            try {
                Scanner scanner = new Scanner(socket.getInputStream());
                String message = scanner.nextLine();
                // TODO parse message
                System.out.println(message);
            } catch (IOException e) {
                System.out.println(e);
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

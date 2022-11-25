package com.example.aaroncorona_cs56_proj8.server;

import com.example.aaroncorona_cs56_proj8.proxy.Bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Helper class to handle individual Socket connections
public final class BankServerSocketHandler implements Bank, Runnable {
    private Socket socket;
    private ObjectInputStream fromClient;
    private ObjectOutputStream toClient;

    // All threads should reference the same immutable account map
    private static Map<Integer, Integer> accountBalance;

    protected BankServerSocketHandler(Socket socket) {
        this.socket = socket;

        createBankServerSocketStreams();

        // Create immutable map
        accountBalance = new HashMap<>();
        accountBalance = Collections.unmodifiableMap(accountBalance);
    }

    // Helper method to create a new socket and stream with the Client
    private void createBankServerSocketStreams() {
        try {
            // Create a socket along with output & input streams to the server
            fromClient = new ObjectInputStream(socket.getInputStream());
            toClient = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            // Get the client request object, then send a response String
            try {
                // Parse the client request, create the requested response, then send it back to the client
                BankServerResponse response = buildResponseFromRequest(fromClient.readObject().toString());
                toClient.writeObject(response);
                // Add the status of the response to the Server GUI
                BankServer.addStatusText(String.valueOf(response));
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
        }
    }

    // Helper method to parse the client's request and return a response
    private BankServerResponse buildResponseFromRequest(String request) {
        // Parse request
        String requestType = getNextWordInString(request, "Request");
        int acctNum = Integer.parseInt(getNextWordInString(request, "Account"));
        int amount = Integer.parseInt(getNextWordInString(request, "Amount"));
        String result = "";
        // Perform requested Bank action
        if(requestType.equals("Balance")) {
            result = getBalance(acctNum);
        } else if(requestType.equals("Deposit")) {
            result = makeDeposit(acctNum, amount);
        } else if(requestType.equals("Withdraw")) {
            result = makeWithdraw(acctNum, amount);
        }
        // Get status (successful or failed request)
        String status = "Success";
        if(result.contains("Error")
             || result.length() == 0) {
            status = "Failed";
        }
        // Get the new Balance after the Bank action
        int endBalance = Integer.parseInt(getNextWordInString(result, "Balance"));
        // Return the full Server Response message
        BankServerResponse response = new BankServerResponse(status, requestType, acctNum, endBalance);
        return response;
    }

    // Helper method to parse the value after a given word in a string to find a response result
    private String getNextWordInString(String fullString, String startWord) {
        String[] words = fullString.split("[;:]");
        for(int i=0; i<words.length; i++) {
            if(words[i].trim().equals(startWord)) {
                return words[i+1].trim();
            }
        }
        return null;
    }

    // Lookup the balance of the account in the hashmap (or 0 if it doesn't exist)
    @Override
    public String getBalance(int acctNum) {
        int balance = 0;
        if(accountBalance.containsKey(acctNum)) {
            balance = accountBalance.get(acctNum);
        }
        return "Balance: " + balance;
    }

    // Make a deposit and return the new balance
    @Override
    public String makeDeposit(int acctNum, int amount) {
        int balance = 0;
        // Make the deposit if the account exists
        if(accountBalance.containsKey(acctNum)) {
            balance = accountBalance.get(acctNum);
            balance += amount;
            immutableMapUpdate(acctNum, balance);
        } else {
            // Otherwise, create a new account with the deposit as the balance
            balance = amount;
            immutableMapUpdate(acctNum, balance);
        }
        return "Balance: " + balance;
    }

    // Make a withdrawal and return the new balance
    @Override
    public String makeWithdraw(int acctNum, int amount) {
        int balance = 0;
        // Start the withdrawal if the account exists
        if(accountBalance.containsKey(acctNum)) {
            // Check if the account balance has enough for the withdrawal
            balance = accountBalance.get(acctNum);
            if(balance >= amount) {
                balance -= amount;
                immutableMapUpdate(acctNum, balance);
            } else {
                // Return an error if there is not enough for the withdrawal
                return "Error: Insufficient Funds for the Withdrawal; " + "Balance: " + balance;
            }
        }
        return "Balance: " + balance;
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

    // Helper method to rebuild the hashmap in a new memory slot on every update for better thread safety
    // Note: The pointer is immutable (not final) and the object is mutable, hence the need for synchronization
    private static synchronized void immutableMapUpdate(int key, int value) {
        // Create a copy of the map in a different memory location and update the object
        Map<Integer, Integer> clone = new HashMap<>(accountBalance);
        clone.put(key, value);
        // Point the hashmap variable to the new location to maintain immutability
        accountBalance = clone;
    }
}

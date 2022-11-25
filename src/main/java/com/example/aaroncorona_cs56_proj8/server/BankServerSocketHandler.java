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
    private ObjectInputStream fromClient;
    private ObjectOutputStream toClient;

    // All threads should reference the same account map
    private static HashMap<Integer, Integer> accountBalance;

    protected BankServerSocketHandler(Socket socket) {
        this.socket = socket;

        createBankServerSocketStreams();

        accountBalance = new HashMap<>();
    }

    // Helper method to create a new socket and stream with the Client
    private void createBankServerSocketStreams() {
        try {
            // Create a socket along with output & input streams to the server
            System.out.println("creating input stream on the Server side...");
            fromClient = new ObjectInputStream(socket.getInputStream());
            System.out.println("creating output stream on the Server side...");
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
                System.out.println("parsing request from input stream on the Server side...");
                BankServerResponse response = buildResponseFromRequest(fromClient.readObject().toString());
                System.out.println(response); // todo delete
                toClient.writeObject(response);
                // TODO update Server GUI label
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
        if(result == null
             || result.length() == 0) {
            status = "Failed";
        }
        // Get the new Balance after the Bank action
        int endBalance;
        if(status.equals("Success")) {
            endBalance = Integer.parseInt(getNextWordInString(result, "Balance"));
        } else {
            endBalance = 0;
        }
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
            accountBalance.put(acctNum, balance);
        } else {
            // Otherwise, create a new account with the deposit as the balance
            balance = amount;
            accountBalance.put(acctNum, balance);
        }
        return "Balance: " + balance;
        // TODO use immutableMapUpdate
    }

    // Make a withdrawal and return the new balance
    @Override
    public String makeWithdraw(int acctNum, int amount) {
        int balance = 0;
        // Make the withdrawal if the account exists
        if(accountBalance.containsKey(acctNum)) {
            balance = accountBalance.get(acctNum);
            balance -= amount;
            accountBalance.put(acctNum, balance);
        }
        return "Balance: " + balance;
        // TODO use immutableMapUpdate
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

    // Helper method to rebuild the hashmap in a new memory slot for better thread safety
    private synchronized void immutableMapUpdate(int key, int value) {
        // TODO write logic
    }
}

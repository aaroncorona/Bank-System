package com.example.aaroncorona_cs56_proj8;

// This is a user-facing Proxy for a real Bank Server
public final class BankProxy implements Bank {

    private BankServer realBank;

    public BankProxy() {
        realBank = new BankServer();
    }

    @Override
    public double getBalance(int acctNum) {
        // Request balance if valid request
        if(acctNum > 0) {
            return realBank.getBalance(acctNum);
        }
        return 0;
    }

    @Override
    public void makeDeposit(int acctNum, int amount) {
        // Make deposit if valid request
        if(acctNum > 0 && amount > 0) {
            realBank.makeDeposit(acctNum, amount);
        }
    }

    @Override
    public void makeWithdraw(int acctNum, int amount) {
        // Check if there are enough funds for the withdrawal on behalf of the Server. Otherwise, make the withdrawal
        if(realBank.getBalance(amount) < amount
           && acctNum > 0) {
            realBank.makeWithdraw(acctNum, amount);
        }
    }

    @Override
    public String getLastResultMsg() {
        return realBank.getLastResultMsg();
    }

    @Override
    public void endConnection() {
         // Close the client thread TODO
    }
}

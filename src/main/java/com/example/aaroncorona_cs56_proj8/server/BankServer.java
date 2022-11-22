package com.example.aaroncorona_cs56_proj8.server;

// Server that performs the Bank actions
public final class BankServer implements Bank {
    // TODO

    // Protected constructor to force the use of the Proxy
    protected BankServer() {}

    @Override
    public double getBalance(int acctNum) {
        return 0;
    }

    @Override
    public void makeDeposit(int acctNum, int amount) {

    }

    @Override
    public void makeWithdraw(int acctNum, int amount) {

    }

    @Override
    public String getLastResultMsg() {
        return null;
    }

    @Override
    public void endConnection() {

    }
}

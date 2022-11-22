package com.example.aaroncorona_cs56_proj8.server;

// Blueprint for Bank functionality
public interface Bank {
    double getBalance(int acctNum);
    void makeDeposit(int acctNum, int amount);
    void makeWithdraw(int acctNum, int amount);
    String getLastResultMsg();
    void endConnection();
}

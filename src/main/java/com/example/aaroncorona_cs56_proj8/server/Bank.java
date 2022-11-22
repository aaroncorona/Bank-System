package com.example.aaroncorona_cs56_proj8.server;

// Blueprint for Bank functionality
public interface Bank {
    String getBalance(int acctNum);
    String makeDeposit(int acctNum, int amount);
    String makeWithdraw(int acctNum, int amount);
    String endConnection();
}

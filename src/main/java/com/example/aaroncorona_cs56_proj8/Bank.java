package com.example.aaroncorona_cs56_proj8;

public interface Bank {
    double getBalance(int acctNum);
    void makeDeposit(int acctNum);
    void makeWithdraw(int acctNum);
}

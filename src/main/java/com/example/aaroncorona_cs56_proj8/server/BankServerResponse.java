package com.example.aaroncorona_cs56_proj8.server;

import java.io.Serializable;

// Wrapper class for sending responses (as an obj) over a stream. Acts as a Helper class to assist the Server
public final class BankServerResponse implements Serializable {

    private String status;
    private String requestType;
    private int acctNum;
    private int balance;

    protected BankServerResponse(String status, String requestType, int acctNum, int balance) {
        this.requestType = requestType;
        this.acctNum = acctNum;
        this.balance = balance;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status: " + status
                + "; Request: " + requestType
                + "; Account: " + acctNum
                + "; Balance: " + balance;
    }
}

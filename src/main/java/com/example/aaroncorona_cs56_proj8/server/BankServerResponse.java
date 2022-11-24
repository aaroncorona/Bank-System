package com.example.aaroncorona_cs56_proj8.server;

import java.io.Serializable;

// Helper class to assist the Proxy with making requests over a stream
public class BankServerResponse implements Serializable {

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
        return "Status: " + status + " Request: " + requestType + "; Account: " + acctNum +
                ";\n The account now has a balance of: " + balance;
    }
}

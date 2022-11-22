package com.example.aaroncorona_cs56_proj8.proxy;

import java.io.Serializable;

// Helper class to assist the Proxy with making requests over a stream
public class BankServerRequest implements Serializable {
    private String requestType;
    private int acctNum;
    private int amount;

    protected BankServerRequest(String requestType, int acctNum, int amount) {
        this.requestType = requestType;
        this.acctNum = acctNum;
        this.amount = amount;
    }

    protected BankServerRequest(String requestType, int acctNum) {
        this.requestType = requestType;
        this.acctNum = acctNum;
        this.amount = 0;
    }

    @Override
    public String toString() {
        return "Request: " + requestType + "; For Account: " + acctNum + "; Regarding Amount: " + amount;
    }
}

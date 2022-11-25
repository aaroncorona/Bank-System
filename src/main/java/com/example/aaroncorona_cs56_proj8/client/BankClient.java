package com.example.aaroncorona_cs56_proj8.client;

// Driver to launch the Client GUI
public class BankClient {

    public static void main(String[] args) {
        // Launch a client GUI using the proxy
        BankClientGUI gui = new BankClientGUI();
        Thread guiThread = new Thread(gui);
        guiThread.start();
    }
}


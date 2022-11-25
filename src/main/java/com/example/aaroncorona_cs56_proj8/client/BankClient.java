/***************************************************************************
 Author: Aaron Corona
 Date: Nov 25th, 2022
 CS56 Project #8 - Bank System
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj8.client;

import com.example.aaroncorona_cs56_proj8.proxy.Bank;
import com.example.aaroncorona_cs56_proj8.proxy.BankProxy;

// Driver to launch the Proxy and Client GUI
public class BankClient {

    public static void main(String[] args) {
        // Create bank proxy
        Bank bank = new BankProxy();

        // Launch a client GUI using the proxy
        BankClientGUI gui = new BankClientGUI(bank);
        Thread guiThread = new Thread(gui);
        guiThread.start();
    }
}


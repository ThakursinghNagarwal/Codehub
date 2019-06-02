package com.example1.jayprakash.bluetoothapps;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by jayprakash on 5/1/2018.
 */

public class Connections {
    private static boolean state = false;

    public static boolean blueTooth() {

        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (!bluetooth.isEnabled()) {
            System.out.println("Bluetooth is Disable...");
            state = true;
        } else if (bluetooth.isEnabled()) {
            String address = bluetooth.getAddress();
            String name = bluetooth.getName();
            System.out.println(name + " : " + address);
            state = false;
        }
        return state;
    }
}

package com.example1.jayprakash.bluetoothapps;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 12;
    private TextView out;
    private BluetoothAdapter adapter;
    LinearLayout li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        out = (TextView) findViewById(R.id.tvBluetoothInfo);
        li=(LinearLayout)findViewById(R.id.li);
        setBluetoothData();

        if (Connections.blueTooth()) {
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        out.setText("");
        setBluetoothData();
    }

    private void setBluetoothData() {

        // Getting the Bluetooth adapter
        adapter = BluetoothAdapter.getDefaultAdapter();
        out.append("\nAdapter: " + adapter.toString() + "\n\nName: "
                + adapter.getName() + "\nAddress: " + adapter.getAddress());

        // Check for Bluetooth support in the first place
        // Emulator doesn't support Bluetooth and will return null

        if (adapter == null) {
            Toast.makeText(this, "Bluetooth NOT supported. Aborting.",
                    Toast.LENGTH_LONG).show();
        }


        // Starting the device discovery
        out.append("\n\nStarting discovery...");
        adapter.startDiscovery();
        out.append("\nDone with discovery...\n");

        // Listing paired devices
        out.append("\nDevices Pared:");
        Set<BluetoothDevice> devices = adapter.getBondedDevices();
        int i=0;
        for (BluetoothDevice device : devices) {
            out.append("\nFound device: " + device.getName() + " Add: "
                    + device.getAddress());

            Button bt=new Button(this);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                   startActivity(intent);
                }
            });
            bt.setText(""+device.getName());
            bt.setId(i++);
            li.addView(bt);


        }
    }
}

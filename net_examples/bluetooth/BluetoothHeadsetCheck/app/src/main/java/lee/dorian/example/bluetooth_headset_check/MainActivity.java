package lee.dorian.example.bluetooth_headset_check;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvBluetoothHeadsetConnection;

    private BluetoothAdapter btAdapter;
    private BluetoothHeadset btHeadset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        btAdapter.closeProfileProxy(BluetoothProfile.HEADSET, btHeadset);
        unregisterReceiver(btReceiver);
    }

    private void init() {
        tvBluetoothHeadsetConnection = (TextView)findViewById(R.id.tvBluetoothHeadsetConnection);

        if (!BluetoothUtils.isBluetoothSupported()) {
            tvBluetoothHeadsetConnection.setText("This device does not support Bluetooth.");
            return;
        }
        else if (!BluetoothUtils.isBluetoothEnabled()) {
            tvBluetoothHeadsetConnection.setText("Bluetooth is disabled now.");
        }

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btAdapter.getProfileProxy(this, profileListener, BluetoothProfile.HEADSET);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        registerReceiver(btReceiver, filter);
    }

    private BluetoothProfile.ServiceListener profileListener = new BluetoothProfile.ServiceListener() {
        @Override
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == BluetoothProfile.HEADSET) {
                btHeadset = (BluetoothHeadset)proxy;
                if (BluetoothUtils.isBluetoothHeadsetConnected(btHeadset)) {
                    tvBluetoothHeadsetConnection.setText("Bluetooth headset is connected.");
                }
                else {
                    tvBluetoothHeadsetConnection.setText("No Bluetooth headset is connected.");
                }
            }
        }

        @Override
        public void onServiceDisconnected(int profile) {
            if (profile == BluetoothProfile.HEADSET) {
                btHeadset = null;
                tvBluetoothHeadsetConnection.setText("Bluetooth headset is disconnected.");
            }
        }
    };

    private final BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    Toast.makeText(MainActivity.this, "BT headset connected.", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    Toast.makeText(MainActivity.this, "BT headset disconnected.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
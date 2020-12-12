package lee.dorian.example.bluetooth_headset_check;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;

import java.util.List;

public class BluetoothUtils {

    public static boolean isBluetoothSupported() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        return (btAdapter != null) ? true : false;
    }

    public static boolean isBluetoothEnabled() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        return btAdapter.isEnabled() ? true : false;
    }

    public static boolean isBluetoothHeadsetConnected(BluetoothHeadset btHeadset) {
        if (btHeadset == null) {
            return false;
        }

        List<BluetoothDevice> btDeviceList = btHeadset.getConnectedDevices();

        for (BluetoothDevice btDevice : btDeviceList) {
            int connectionState = btHeadset.getConnectionState(btDevice);
            if (connectionState == BluetoothHeadset.STATE_CONNECTED) {
                return true;
            }
        }

        return false;
    }

}

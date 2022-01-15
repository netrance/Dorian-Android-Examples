package lee.dorian.android.reading_global_settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Global
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvSettings = findViewById<TextView>(R.id.tvSettings).apply {
            text = readSettings()
        }
    }

    fun readSettings(): String {
        return """
            • ADB enabled: ${readSettingsValue(Global.ADB_ENABLED)}
            • Airplane mode on: ${readSettingsValue(Global.AIRPLANE_MODE_ON)}
            • Airplane mode radios: ${readSettingsValue(Global.AIRPLANE_MODE_RADIOS)}
            • Always finish activities: ${readSettingsValue(Global.ALWAYS_FINISH_ACTIVITIES)}
            • Apply ramping ringer: ${readSettingsValue(Global.APPLY_RAMPING_RINGER)}
            • Auto time: ${readSettingsValue(Global.AUTO_TIME)}
            • Auto time zone: ${readSettingsValue(Global.AUTO_TIME_ZONE)}
            • Bluetooth on: ${readSettingsValue(Global.BLUETOOTH_ON)}
            • Boot count: ${readSettingsValue(Global.BOOT_COUNT)}
            • Data roaming: ${readSettingsValue(Global.DATA_ROAMING)}
            • Debug app: ${readSettingsValue(Global.DEBUG_APP)}
            • Development Settings Enabled: ${readSettingsValue(Global.DEVELOPMENT_SETTINGS_ENABLED)}
            • Device name: ${readSettingsValue(Global.DEVICE_NAME)}
            • Device provisioned: ${readSettingsValue(Global.DEVICE_PROVISIONED)}
            • HTTP proxy: ${readSettingsValue(Global.HTTP_PROXY)}
            • Mode ringer: ${readSettingsValue(Global.MODE_RINGER)}
            • Network preference: ${readSettingsValue(Global.NETWORK_PREFERENCE)}
            • Radio bluetooth: ${readSettingsValue(Global.RADIO_BLUETOOTH)}
            • Radio cell: ${readSettingsValue(Global.RADIO_CELL)}
            • Radio NFC: ${readSettingsValue(Global.RADIO_NFC)}
            • Radio Wi-Fi: ${readSettingsValue(Global.RADIO_WIFI)}
            • Stay on while plugged-in: ${readSettingsValue(Global.STAY_ON_WHILE_PLUGGED_IN)}
            • Transition animation scale: ${readSettingsValue(Global.TRANSITION_ANIMATION_SCALE)}
            • USB mass storage enabled: ${readSettingsValue(Global.USB_MASS_STORAGE_ENABLED)}
            • Use Google email: ${readSettingsValue(Global.USE_GOOGLE_MAIL)}
            • Wait for debugger: ${readSettingsValue(Global.WAIT_FOR_DEBUGGER)}
            • Wi-Fi device owner configs lockdown: ${readSettingsValue(Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN)}
            • Wi-Fi max DHCP retry count: ${readSettingsValue(Global.WIFI_MAX_DHCP_RETRY_COUNT)}
            • Wi-Fi on: ${readSettingsValue(Global.WIFI_ON)}
            • Wi-Fi watchdog on: ${readSettingsValue(Global.WIFI_WATCHDOG_ON)}
            • Window animation scale: ${readSettingsValue(Global.WINDOW_ANIMATION_SCALE)}
        """.trimIndent()
    }

    fun readBooleanValue(value: Int): String {
        when (value) {
            0 -> return "False"
            1 -> return "True"
            else -> return "Undefined"
        }
    }

    fun readSettingsValue(name: String): String {
        val cr = contentResolver

        try {
            when (name) {
                Global.ADB_ENABLED -> return readBooleanValue(Global.getInt(cr, Global.ADB_ENABLED))
                Global.AIRPLANE_MODE_ON -> return readBooleanValue(Global.getInt(cr, Global.AIRPLANE_MODE_ON))
                Global.AIRPLANE_MODE_RADIOS -> return "${Global.getString(cr, Global.AIRPLANE_MODE_RADIOS)}"
                Global.ALWAYS_FINISH_ACTIVITIES -> return "${Global.getInt(cr, Global.ALWAYS_FINISH_ACTIVITIES)}"
                Global.ANIMATOR_DURATION_SCALE -> return "${Global.getFloat(cr, Global.ANIMATOR_DURATION_SCALE)}"
                Global.APPLY_RAMPING_RINGER -> return readBooleanValue(Global.getInt(cr, Global.APPLY_RAMPING_RINGER))
                Global.AUTO_TIME -> return readBooleanValue(Global.getInt(cr, Global.AUTO_TIME))
                Global.AUTO_TIME_ZONE -> return readBooleanValue(Global.getInt(cr, Global.AUTO_TIME_ZONE))
                Global.BLUETOOTH_ON -> return readBooleanValue(Global.getInt(cr, Global.BLUETOOTH_ON))
                Global.BOOT_COUNT -> return "${Global.getInt(cr, Global.BOOT_COUNT)}"
                Global.DATA_ROAMING -> return readBooleanValue(Global.getInt(cr, Global.DATA_ROAMING))
                Global.DEBUG_APP -> return "${Global.getString(cr, Global.DEBUG_APP)}"
                Global.DEVELOPMENT_SETTINGS_ENABLED -> return readBooleanValue(Global.getInt(cr, Global.DEVELOPMENT_SETTINGS_ENABLED))
                Global.DEVICE_NAME -> return "${Global.getString(cr, Global.DEVICE_NAME)}"
                Global.DEVICE_PROVISIONED -> return readBooleanValue(Global.getInt(cr, Global.DEVICE_PROVISIONED))
                Global.HTTP_PROXY -> return "${Global.getString(cr, Global.HTTP_PROXY)}"
                Global.MODE_RINGER -> return "${Global.getString(cr, Global.MODE_RINGER)}"
                Global.NETWORK_PREFERENCE -> return "${Global.getString(cr, Global.NETWORK_PREFERENCE)}"
                Global.RADIO_BLUETOOTH -> return "${Global.getString(cr, Global.RADIO_BLUETOOTH)}"
                Global.RADIO_CELL -> return "${Global.getString(cr, Global.RADIO_CELL)}"
                Global.RADIO_NFC -> return "${Global.getString(cr, Global.RADIO_NFC)}"
                Global.RADIO_WIFI -> return "${Global.getString(cr, Global.RADIO_WIFI)}"
                Global.STAY_ON_WHILE_PLUGGED_IN -> return "${Global.getInt(cr, Global.STAY_ON_WHILE_PLUGGED_IN)}"
                Global.TRANSITION_ANIMATION_SCALE -> return "${Global.getFloat(cr, Global.TRANSITION_ANIMATION_SCALE)}"
                Global.USB_MASS_STORAGE_ENABLED -> return "${readBooleanValue(Global.getInt(cr, Global.USB_MASS_STORAGE_ENABLED))}"
                Global.USE_GOOGLE_MAIL -> return "${readBooleanValue(Global.getInt(cr, Global.USE_GOOGLE_MAIL))}"
                Global.WAIT_FOR_DEBUGGER -> return "${readBooleanValue(Global.getInt(cr, Global.WAIT_FOR_DEBUGGER))}"
                Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN -> return "${Global.getInt(cr, Global.WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN)}"
                Global.WIFI_MAX_DHCP_RETRY_COUNT -> return "${Global.getInt(cr, Global.WIFI_MAX_DHCP_RETRY_COUNT)}"
                Global.WIFI_ON -> return "${readBooleanValue(Global.getInt(cr, Global.WIFI_ON))}"
                Global.WIFI_WATCHDOG_ON -> return "${readBooleanValue(Global.getInt(cr, Global.WIFI_WATCHDOG_ON))}"
                Global.WINDOW_ANIMATION_SCALE -> return "${Global.getFloat(cr, Global.WINDOW_ANIMATION_SCALE)}"
            }
        }
        catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }

        return "Undefined"
    }

}
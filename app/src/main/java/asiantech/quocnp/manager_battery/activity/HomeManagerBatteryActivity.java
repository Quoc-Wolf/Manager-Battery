package asiantech.quocnp.manager_battery.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

import asiantech.quocnp.manager_battery.R;

@EActivity(R.layout.activity_home_manager_battery)
public class HomeManagerBatteryActivity extends BaseActivity {
    @ViewById(R.id.tvStatus)
    TextView mTvStatus;
    @ViewById(R.id.progressBarStatus)
    ProgressBar mTvProgressBarStatus;
    private Handler mHandler;
    private TextToSpeech mTextToSpeech;
    private BroadcastReceiver batteryDetailsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPresent = intent.getBooleanExtra("present", false);
            String technology = intent.getStringExtra("technology");
            int icon = intent.getIntExtra("iconSmall", 0);
            int plugged = intent.getIntExtra("plugged", -1);
            int scale = intent.getIntExtra("scale", -1);
            int health = intent.getIntExtra("health", 0);
            int status = intent.getIntExtra("status", 0);
            int rawlevel = intent.getIntExtra("level", -1);
            int voltage = intent.getIntExtra("voltage", 0);
            int temperature = intent.getIntExtra("temperature", 0);
            int property = intent.getIntExtra("property",0);
            int level = 0;
            Bundle bundle = intent.getExtras();
            Log.i("BatteryLevel", bundle.toString());
            if (isPresent) {
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                String info = "Battery Level: " + level + "%\n";
                info += ("icon:" + icon + "\n");
                info += ("Technology: " + technology + "\n");
                info += ("Plugged: " + getPlugTypeString(plugged) + "\n");
                info += ("Health: " + getHealthString(health) + "\n");
                info += ("Status: " + getStatusString(status) + "\n");
                info += ("Voltage: " + voltage / 1000.0f + "V" + "\n");
                info += ("Temperature: " + (temperature - 273) + Character.toString((char) 176) + "C" + "\n");
                info += ("property:" + getPropertyString(property) +"\n");
                setBatteryLevelText(info + "\n\n" + bundle.toString());
            } else {
                setBatteryLevelText("Battery not present!!!");
            }
        }
    };
    private void initEvent(){
        mTextToSpeech = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int arg0) {
                        mTextToSpeech.setLanguage(Locale.UK);
                    }
                });
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //noinspection deprecation
                mTextToSpeech.speak("Well come to life battery", TextToSpeech.QUEUE_FLUSH, null);
            }
        };
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(new Message());
            }
        },1000);
    }
    @Override
    void afterView() {
        initEvent();
        registerBatteryLevelReceiver();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(batteryDetailsReceiver);
        super.onDestroy();
    }

    /**
     * This is method register Broadcast Receiver
     */
    private void registerBatteryLevelReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryDetailsReceiver, intentFilter);
    }

    /**
     * @param plugged USB or AC or not;
     * @return plugType
     */
    private String getPlugTypeString(int plugged) {
        String plugType;
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                plugType = "WIRELESS";
                break;
            default:
                plugType = "Unknown";
        }
        return plugType;
    }

    /**
     * @param health
     * @return
     */
    private String getHealthString(int health) {
        String healthString;
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                healthString = "Cold";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                healthString = "Unknown";
                break;
            default:
                healthString = "Not Confirm";
        }
        return healthString;
    }

    /**
     * @param status String
     * @return status battery
     */
    private String getStatusString(int status) {
        String statusString;
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                statusString = "Unknown";
                break;
            default:
                statusString = "Not Confirm";
        }
        return statusString;
    }

    /**
     *
     * @param property
     * @return
     */
    private String getPropertyString(int property) {
        String propertyString;
        switch (property) {
            case BatteryManager.BATTERY_PROPERTY_CAPACITY:
                propertyString = "Capacity";
                break;
            case BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER:
                propertyString = "Charge Counter";
                break;
            case BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE:
                propertyString = "Current Average";
                break;
            case BatteryManager.BATTERY_PROPERTY_CURRENT_NOW:
                propertyString = "Current Now";
                break;
            case BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER:
                propertyString = "Energy Counter";
                break;
            default:
                propertyString = "Not confirm";
        }
        return propertyString;
    }

    /**
     * @param text
     */
    private void setBatteryLevelText(String text) {
        mTvStatus.setText(text);
    }
}

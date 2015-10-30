package asiantech.quocnp.manager_battery.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by quoc on 22/10/2015.
 */
public class BatteryServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("FOX","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("FOX1", "Received start id " + startId + ": " + intent);
        return START_STICKY;
    }
}

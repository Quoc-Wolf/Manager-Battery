package asiantech.quocnp.manager_battery.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import asiantech.quocnp.manager_battery.R;
import asiantech.quocnp.manager_battery.views.CustomProgressBar;

@SuppressWarnings("ALL")
@EActivity(R.layout.activity_battery_status)
public class BatteryStatusActivity extends BaseActivity{

    @ViewById(R.id.customProgressBar)
    CustomProgressBar mCustomProgressBar;

    @Override
    void afterView() {
        //register Receiver
        registerBatteryStatusReceiver();
        // Set up the white button on the lower right corner
        // more or less with default parameter
        final ImageView imgViewFabButton = new ImageView(this);
        imgViewFabButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_perm_data_setting_black_36dp));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(imgViewFabButton)
                .build();

        SubActionButton.Builder subBuilder = new SubActionButton.Builder(this);
        final ImageView imgViewFist = new ImageView(this);
        ImageView imgViewSecond = new ImageView(this);
        ImageView imgViewThird = new ImageView(this);
        ImageView imgViewFourth = new ImageView(this);

        //set icon for menu button
        imgViewFist.setImageDrawable(getResources().getDrawable(R.drawable.ic_insert_emoticon_black_24dp));
        imgViewSecond.setImageDrawable(getResources().getDrawable(R.drawable.ic_insert_emoticon_black_24dp));
        imgViewThird.setImageDrawable(getResources().getDrawable(R.drawable.ic_insert_emoticon_black_24dp));
        imgViewFourth.setImageDrawable(getResources().getDrawable(R.drawable.ic_insert_emoticon_black_24dp));

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(subBuilder.setContentView(imgViewFist).build())
                .addSubActionView(subBuilder.setContentView(imgViewSecond).build())
                .addSubActionView(subBuilder.setContentView(imgViewThird).build())
                .addSubActionView(subBuilder.setContentView(imgViewFourth).build())
                .attachTo(rightLowerButton)
                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                imgViewFabButton.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(imgViewFabButton, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                imgViewFabButton.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(imgViewFabButton, pvhR);
                animation.start();
            }
        });
        //==========================HANDLE TOUCH ITEM MENU FLOATTING BUTTON =====================

        imgViewFist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"aaaaa",Toast.LENGTH_SHORT).show();
                DemoActivity_.intent(BatteryStatusActivity.this).start();
            }
        });
        imgViewSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Bbbbbb",Toast.LENGTH_SHORT).show();
                Log.i("FOX", "bbbbbbbbbbbbbbb");
            }
        });
        imgViewThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"CCCCCC",Toast.LENGTH_SHORT).show();
                Log.i("FOX", "CCCCCCCCCCCCCCCCCCCCCC");
            }
        });  imgViewFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"DDDDDDDDDDDDD",Toast.LENGTH_SHORT).show();
                Log.i("FOX", "DDDDDDDDDDDDDDDDDDDDDDDD");
            }
        });

        //=====================================END===============================================
    }
    /**
     * This is method register Broadcast Receiver
     */
    private void registerBatteryStatusReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryDetailsReceiver, intentFilter);
    }

    /**
     * This is Broadcast receiver get level perecent battery
     */
    private BroadcastReceiver batteryDetailsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int percentBattery = intent.getIntExtra("level", -1);
            mCustomProgressBar.setPercent(percentBattery);
        }
    };

}

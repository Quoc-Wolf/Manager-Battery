package asiantech.quocnp.manager_battery.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import org.androidannotations.annotations.EActivity;

import asiantech.quocnp.manager_battery.R;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIME = 3000;
    private final Handler mHandler = new Handler();

    @Override
    void afterView() {
       // Transparent Status BarTRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //switch screen home
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                HomeManagerBatteryActivity_.intent(SplashActivity.this).start();
            }
        };
        mHandler.postDelayed(mRunnable,SPLASH_TIME);
    }
}

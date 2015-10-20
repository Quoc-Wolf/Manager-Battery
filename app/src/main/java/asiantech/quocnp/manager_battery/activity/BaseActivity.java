package asiantech.quocnp.manager_battery.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import asiantech.quocnp.manager_battery.App;

/**
 * Created by tientun on 3/5/15.
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    @AfterViews
    protected void initView() {
        this.afterView();
    }

    abstract void afterView();

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();

    }

    /**
     * Clear references
     */
    private void clearReferences() {
        Activity currentActivity = App.getInstance().getCurrentActivity();
        if (currentActivity != null && currentActivity.equals(this)) {
            App.getInstance().setCurrentActivity(null);
        }
    }
}

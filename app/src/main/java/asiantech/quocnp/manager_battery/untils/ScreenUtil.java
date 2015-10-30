package asiantech.quocnp.manager_battery.untils;
/**
 * Copyright © 2015 AsianTech inc.
 * Created by Binc on 08/09/2015.
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * get size Screen
 */
public final class ScreenUtil {
    /**
     * contructor ScreenUtil
     */
    private ScreenUtil() {
    }

    /**
     * This method is used to get height of screen
     *
     * @param context is current context
     * @return return height screen in pixel
     */
    public static int getHeightScreen(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * This method is used to get width of screen
     *
     * @param context is current context
     * @return return width of screen in pixel
     */
    public static int getWidthScreen(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * This method is used to convert dp to pixel
     *
     * @param context is current context
     * @param dp      is value you want to convert for
     * @return return value in pixel
     */
    public static float convertDPToPixels(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (dp * density);
    }

    /**
     * This method is used to get height of status bar
     *
     * @param context is current context
     * @return return height of status bar
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
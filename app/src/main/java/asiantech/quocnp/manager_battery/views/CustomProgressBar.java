package asiantech.quocnp.manager_battery.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import asiantech.quocnp.manager_battery.R;
import asiantech.quocnp.manager_battery.untils.ScreenUtil;

/**
 * @author quoc
 *         Created by quoc on 29/10/2015.
 */
public class CustomProgressBar extends View {
    private final Context mContext;
    private Paint mPaint;
    private int mPercent;

    private float mRate = 4.0f / 1.0f;
    private int mResizeTop = 100;

    /**
     * This is constructor
     *
     * @param context mContext
     */
    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mResizeTop= (int) ScreenUtil.convertDPToPixels(mContext, mResizeTop);
        mRate=ScreenUtil.convertDPToPixels(mContext,1)/1.5f;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        mPaint = new Paint();
        checkLevelBattery();
        mPaint.setFilterBitmap(true);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_status_battery);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int)(300*bitmap.getWidth()/(bitmap.getHeight()*1.0f)*mRate), (int) (300 * mRate), false);

        float xLeft = (getWidth() - bitmap.getWidth()) / 2.0f;
        float yTop = (getHeight() - bitmap.getHeight()) / 2 -mResizeTop+ 98*mRate;
        float xRight = (getWidth() + bitmap.getWidth()) / 2.0f;
        float yBottom = (getHeight() - bitmap.getHeight()) / 2 -mResizeTop+ 247*mRate;

        canvas.drawRect(xLeft, yBottom - (yBottom - yTop) * mPercent / 100.0f, xRight, yBottom, mPaint);
        canvas.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2, (getHeight() - bitmap.getHeight()) / 2-mResizeTop, mPaint);
    }

    public void setPercent(int mPercent) {
        this.mPercent = mPercent;
        //update % level battery
        invalidate();
    }

    /**
     * This is check level % battery
     */
    private void checkLevelBattery() {
        if (mPercent >= 50) {
            mPaint.setColor(Color.GREEN);
        } else if (mPercent >= 20) {
            mPaint.setColor(Color.YELLOW);
        } else {
            mPaint.setColor(Color.RED);
        }
    }
}

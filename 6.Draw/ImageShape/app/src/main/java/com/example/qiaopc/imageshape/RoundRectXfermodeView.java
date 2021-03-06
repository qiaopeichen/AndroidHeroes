package com.example.qiaopc.imageshape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by qiaopc on 2018/1/21 0021.
 * PorterDuffXfermode
 * 有点像数学中的交集、并集这样的概念
 * 它控制的是两个图像间的混合显示模式
 */

public class RoundRectXfermodeView extends View {

    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;

    public RoundRectXfermodeView(Context context) {
        super(context);
        initView();
    }

    public RoundRectXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoundRectXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mOut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);

        // 先用画笔画一个遮罩层
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 80, 80, mPaint);
        canvas.drawCircle(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2, mBitmap.getHeight() / 2, mPaint);

        // 再用带PorterDuffXfermode的画笔将图像画在遮罩层上，DST是前面的图形，SRC是后面的图形
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mOut, 0, 0, null);
    }
}


package com.example.qiaopc.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by qiaopc on 2018/1/6 0006.
 */

public class FlexibleListView extends ListView {

    private static int mMaxOverDistance = 50;
    private Context mContext;

    public FlexibleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public FlexibleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public FlexibleListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        initView();
    }

    private void initView() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverDistance = (int) (density * mMaxOverDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY,
                                   boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY,
                scrollX, scrollY,
                scrollRangeX, scrollRangeY,
                maxOverScrollX, mMaxOverDistance,
                isTouchEvent);
    }
}

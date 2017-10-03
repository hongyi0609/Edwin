package com.bignerdranch.photogallery;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by hongy_000 on 2017/10/3.
 */

public class PreloadGridLayoutManager extends GridLayoutManager {

    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 600;
    private int extraLayoutSpace = -1;
    private Context context;

    public PreloadGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public PreloadGridLayoutManager(Context context, int spanCount, int extraLayoutSpace) {
        super(context, spanCount);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;
    }

    public PreloadGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        this.context = context;
    }

    public PreloadGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        this.context = context;
    }

    public void setExtraLayoutSpace(int extraLayoutSpace) {
        this.extraLayoutSpace = extraLayoutSpace;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return super.getExtraLayoutSpace(state);
    }
}

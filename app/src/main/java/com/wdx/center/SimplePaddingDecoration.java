package com.wdx.center;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 15:45
 */

public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;


    public SimplePaddingDecoration(Context context) {
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;//类似加了一个bottom padding
    }
}


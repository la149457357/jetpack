package com.wdx.center.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wdx.center.R;
import com.wdx.common.utils.DensityUtils;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/7 15:17
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    /**
     * 滑动到最下面时的上拉操作
     */
    private int mTounchslop;

    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;

   // private ListView mListView;
    private RecyclerView mRecycleView;
    /**
     * 按下时的y坐标
     */
    private int mYdown;

    private int mYlast;

    private boolean isLoading = false;

    private OnLoadMoreListener mOnLoadMoreListener;

    private TextView mTvLoadMore;

    private int mVisibleItemCount;

    private int mTotalItemCount;

    Context mContext;
    public MySwipeRefreshLayout(Context context) {
        this(context, null);
        mContext=context;
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    private void init() {
        mTounchslop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(mContext).inflate(R.layout.footer_item, null);
        mTvLoadMore = mListViewFooter.findViewById(R.id.tv_loadmore);
        mTvLoadMore.setText("加载更多");
        LayoutParams layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mListViewFooter.setLayoutParams(layoutParams);

    }

    public void setRecycleView(RecyclerView mRecycleView){
        this.mRecycleView = mRecycleView;
    }
    /**
     * 获取ListView对象
     */


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYdown = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                mYlast = (int) ev.getY();
                break;

            case MotionEvent.ACTION_UP:

                if (canLoad()) {
                    loadData();
                }
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean canLoad() {
        return !isLoading && isPullup()&&isLong();
    }

    private boolean isLong(){
       // recyclerView.computeVerticalScrollExtent() //显示区域的高度
       // recyclerView.computeVerticalScrollOffset() //已经向下滚动的距离，为0时表示已处于顶部
      //  recyclerView.computeVerticalScrollRange() //整体的高度，注意是整体，包括在显示区域之外的

       return mRecycleView.computeVerticalScrollExtent() + mRecycleView.computeVerticalScrollOffset()
                >= mRecycleView.computeVerticalScrollRange();

    }
    private boolean enableBottomLoad() {
        return !isLoading && isBottom();
    }

    private boolean isBottom() {
        //.getLastVisiblePosition()
        if (mRecycleView != null && mRecycleView.getAdapter() != null) {
            return mVisibleItemCount < mTotalItemCount
                    && ((LinearLayoutManager)mRecycleView.getLayoutManager()).findLastVisibleItemPosition() == mRecycleView.getAdapter().getItemCount() - 1;
        }
        return false;
    }

    private boolean isPullup() {
        return mYdown - mYlast >= mTounchslop;
    }


    public void setLoading(boolean loading) {

    }



    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }



    public interface OnLoadMoreListener {

        void onLoadMore();
    }

    private void loadData() {
        if (mOnLoadMoreListener != null) {
            setLoading(true);
            mOnLoadMoreListener.onLoadMore();
            RelativeLayout.LayoutParams layoutParams = getRelativeParms();
            if(mListViewFooter.getParent()==null){
                ((RelativeLayout)this.getParent()).addView(mListViewFooter,layoutParams);
            }else {
                ((RelativeLayout)this.getParent()).removeView(mListViewFooter);
            }


        }
    }
    public RelativeLayout.LayoutParams getRelativeParms(){
        RelativeLayout.LayoutParams rLParams =
                new RelativeLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
        return rLParams;

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        //由于这里child只有一个所以将整个长宽都设置给child
        View child = this.getChildAt(0);
        child.layout(childLeft, childTop, width - getPaddingRight(), height - getPaddingBottom());

    }
}

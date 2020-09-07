package com.wdx.center.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wdx.center.R;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/7 15:17
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout{

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

    public MySwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTounchslop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.footer_item, null);
        mTvLoadMore = (TextView) mListViewFooter.findViewById(R.id.tv_loadmore);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //初始化ListView
        if (mRecycleView == null) {
            getListView();
        }
    }
    public void setRecycleView(RecyclerView mRecycleView){
        this.mRecycleView = mRecycleView;
    }
    /**
     * 获取ListView对象
     */
    private void getListView() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child instanceof ListView) {
                    mRecycleView = (RecyclerView) child;
                    // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                    mRecycleView.addOnScrollListener(new OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView,
                                int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                        }

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                        }
                    });
                }
            }
        }
    }

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

    public void setAdapte(ListView listView, ListAdapter adapter) {
        if (listView != null) {
            listView.addFooterView(mListViewFooter);
            listView.setAdapter(adapter);
            listView.removeFooterView(mListViewFooter);
        }
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

    private void loadData() {
        if (mOnLoadMoreListener != null) {
            setLoading(true);
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void setLoading(boolean loading) {
       /* isLoading = loading;
        if (loading) {
            mRecycleView.getAdapter().addFooterView(mListViewFooter);
        } else {
            mRecycleView.removeFooterView(mListViewFooter);
            mYdown = 0;
            mYlast = 0;
        }*/

    }

    public void setLoadingContext(String string) {
        mTvLoadMore.setText(string);
    }

    public void setLoadingContext(int resId) {
        mTvLoadMore.setText(resId);
    }



    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {

    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }



    public interface OnLoadMoreListener {

        void onLoadMore();
    }


}

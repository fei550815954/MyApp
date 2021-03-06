package cn.kingcd.myapplication.utils.oftenUtils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * ==============================================
 * <p>
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：fei
 * <p>
 * 创建时间：2017/2/15
 * <p>
 * 修订历史：
 * <p>
 * 修订时间：
 * ==============================================
 * ==================《程序员》==================
 * =======十年生死两茫茫，写程序，到天亮。=======
 * ==============千行代码，Bug何处藏。===========
 * =======纵使上线又怎样，朝令改，夕断肠。=======
 * ----------------------------------------------
 * ======领导每天新想法，天天改，日日忙。========
 * =============相顾无言，惟有泪千行。===========
 * ======每晚灯火阑珊处，夜难寐，加班狂。========
 * ==============================================
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    private static final String CustomSwipeRefreshLayout = "CustomSwipeRefreshLayout";
    public OnLoadListener onLoadListener;
    Context context;
    ListView listView;
    float startY = 0;
    float endY = 0;
    private static float touchInstance = 150;
    boolean isLoading = false;
    LinearLayout loadLayout;

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        if (listView == null) {
            if (getChildCount() > 0) {
                for (int i = 0; i < getChildCount(); i++) {
                    if (getChildAt(i) instanceof ListView) {
                        listView = (ListView) getChildAt(i);
                        L.i(CustomSwipeRefreshLayout, "找到了LIstView");
                        initLoadLayout();//初始化加载控件
                        setListViewOnScroll();//滚动监听
                        break;
                    } else {
                        L.i(CustomSwipeRefreshLayout, "不是LIstView的实例");
                    }
                }
                L.i(CustomSwipeRefreshLayout, "LIstView是否为空：" + (listView == null));
            }
        }
        super.onLayout(changed, left, top, right, bottom);
    }


    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    /**
     * 设置滚动监听
     */
    private void setListViewOnScroll() {
        if (listView != null) {
            listView.setOnScrollListener(new OnScrollListener() {
                //正在移动
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    L.i(CustomSwipeRefreshLayout, "" + listView.getLastVisiblePosition());
                    if (canLoadMore()) {
                        loadData();
                    } else {
                        L.i(CustomSwipeRefreshLayout, "不可以加载新数据");
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {
                }
            });
        }
    }

    /**
     * 三个条件可以加载数据
     * 1、滑动距离合适的时候
     * 2、最后一个条目
     * 3、没有正在加载数据
     *
     * @return
     */
    protected boolean canLoadMore() {
        boolean condition1 = false;
        if (!isLoading) {
            condition1 = true;
        }
        boolean condition2 = false;
        if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
            condition2 = true;
        }
        boolean condition3 = false;
        if ((startY - endY) > touchInstance) {
            condition3 = true;
        }
        L.i(CustomSwipeRefreshLayout, "是否正在加载" + condition1 + "是否是最后一个并且已经显示出来" + condition2 + "触摸距离是否合适" + condition3);
        return condition1 && condition2 && condition3;
    }

    /**
     * 接口回调实现自定义加载数据
     */
    protected void loadData() {

        if (onLoadListener != null) {
            if (loadLayout != null) {
                addLoadLayout();//添加footerView
            }
            onLoadListener.onLoad();
        }

    }

    private void addLoadLayout() {
        listView.addFooterView(loadLayout);
        if (listView.getAdapter() instanceof BaseAdapter) {
            BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
            listView.setAdapter(adapter);
            L.i(CustomSwipeRefreshLayout, "是baseAdapter");
        } else {
            L.i(CustomSwipeRefreshLayout, "不是baseAdapter");
        }
    }

    private void removeLoadLayout() {
        listView.removeFooterView(loadLayout);
    }

    public void setOnload(boolean isLoad) {
        isLoading = isLoad;
        if (!isLoad) {
            removeLoadLayout();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //按下时
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            startY = ev.getY();
        }
        //离开时
        else if (ev.getAction() == MotionEvent.ACTION_UP) {
            endY = ev.getY();
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 初始化底部加载视图
     */
    private void initLoadLayout() {
        //布局，由于父控件是ListView，所以 LayoutParams 是AbsListView的LayoutParams
        AbsListView.LayoutParams listLayoutParams = new AbsListView.LayoutParams(listView.getLayoutParams());
        listLayoutParams.width = LayoutParams.MATCH_PARENT;
        listLayoutParams.height = 100;
        loadLayout = new LinearLayout(context);
        loadLayout.setOrientation(LinearLayout.HORIZONTAL);
        loadLayout.setLayoutParams(listLayoutParams);
        loadLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        //dialog
        android.view.ViewGroup.LayoutParams layoutParams = new android.view.ViewGroup.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleInverse);
        progressBar.setLayoutParams(layoutParams);
        //textview
        android.view.ViewGroup.LayoutParams layoutParams2 = new android.view.ViewGroup.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(context);
        textView.setText("正在加载.....");
        textView.setTextSize(15);
        textView.setLayoutParams(layoutParams2);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //设置子控件
        loadLayout.addView(progressBar);
        loadLayout.addView(textView);
    }

    interface OnLoadListener {
        public void onLoad();
    }

}
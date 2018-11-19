package cn.kingcd.myapplication.utils.views;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import cn.kingcd.myapplication.utils.oftenUtils.L;

/**
 * ==============================================
 * <p>自定义解决嵌套轮播图滑动冲突问题
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：fei
 * <p>
 * 创建时间：2017/7/27
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
public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    float lastx = 0;
    float lasty = 0;
    boolean ismovepic = false;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {



        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            lastx = ev.getX();
            lasty = ev.getY();
            ismovepic = false;
            return super.onInterceptTouchEvent(ev);
        }

        final int action = MotionEventCompat.getActionMasked(ev);

        int x2 = (int) Math.abs(ev.getX() - lastx);
        int y2 = (int) Math.abs(ev.getY() - lasty);

        //滑动图片最小距离检查

        L.v("滑动差距 - >" + x2 + "--" + y2);
        if (x2>y2){
            if (x2>=100){
                ismovepic = true;
            }
            return false;
        }

        //是否移动图片(下拉刷新不处理)
        if (ismovepic){
            L.v("滑动差距 - >" + x2 + "--" + y2);
            return false;
        }

        boolean isok = super.onInterceptTouchEvent(ev);

        L.v("isok ->" + isok);

        return isok;
    }
}

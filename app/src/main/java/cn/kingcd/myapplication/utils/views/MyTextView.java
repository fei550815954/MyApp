package cn.kingcd.myapplication.utils.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * ==============================================
 * <p>不需要获取焦点也能实现跑马灯
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：fei
 * <p>
 * 创建时间：2017/10/12
 * <p>
 * 修订历史：
 * <p>
 *  android:singleLine=true
 *  android:ellipsize=marquee
 *  android:focusableInTouchMode=true
 *  android:focusable=true
 *  android:marqueeRepeatLimit=marquee_forever这个属性并不是必须的，可以指定具体的滚动次数，也可以使用默认
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

public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused)
            super.onWindowFocusChanged(focused);
    }

    @Override
    public boolean isFocused() {
        return true;//一直返回true，假装这个控件一直获取着焦点
    }
}

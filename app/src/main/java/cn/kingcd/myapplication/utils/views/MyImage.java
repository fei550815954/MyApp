package cn.kingcd.myapplication.utils.views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/7/5.
 * 自定义iamgeView 加速图片回收
 */
public class MyImage extends android.support.v7.widget.AppCompatImageView {
    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImage(Context context) {
        super(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}

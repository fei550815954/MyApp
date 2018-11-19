package cn.kingcd.myapplication.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ==============================================
 * <p>重写该方法，达到使ListView适应ScrollView的效果
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：fei
 * <p>
 * 创建时间：2017/05/15
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
    public class ListViewForScrollView extends ListView {
        public ListViewForScrollView(Context context) {
            super(context);
        }
        public ListViewForScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        public ListViewForScrollView(Context context, AttributeSet attrs,
                                     int defStyle) {
            super(context, attrs, defStyle);
        }
        @Override
        /**
         * 重写该方法，达到使ListView适应ScrollView的效果
         */
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }


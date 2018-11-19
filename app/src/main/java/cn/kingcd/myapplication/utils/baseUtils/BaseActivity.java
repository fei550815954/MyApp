package cn.kingcd.myapplication.utils.baseUtils;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.lzy.okgo.OkGo;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;


import butterknife.ButterKnife;
import cn.kingcd.myapplication.utils.oftenUtils.ActivityUtils;


/**
 * Activity的基类
 *
 * @author lilingfei
 */

public abstract class BaseActivity extends AppCompatActivity {
    public LoadingDialog ld;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        ButterKnife.bind(this);
        initView();
        initData();
        initHandler();
        initListener();
        ActivityUtils.getActivityManager().addActivity(this);
    }
    /**
     * dialog显示
     * @param s
     */
    protected void showDialog(String s) {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(s);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     *dialog关闭
     */
    protected void stopDialog() {
        if (null != dialog) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     *
     * @param data
     */
    protected void startLoadDialog(String data) {
        //必须再new一个他源码是成功或者失败把这个对象删除了，不然再次使用这个会空指针
        ld = new LoadingDialog(this);
        ld.setLoadingText(data)
                .setSuccessText("加载成功!")//成功时的文字
                .setFailedText("加载失败!")//失败时候的文字
                .setInterceptBack(true)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)//速度
                .setRepeatCount(0)//显示几次 默认是0次
                .show();//显示
    }

    /**
     * 下拉刷新状态关闭，下拉刷新为系统自带的
     */
    protected void stopSrl(final SwipeRefreshLayout srl) {
        try {
            srl.post(new Runnable() {
                @Override
                public void run() {
                    if (srl!=null&srl.isRefreshing()){
                        srl.setRefreshing(false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 直接执行下拉刷新状体
     */
    protected void showSrl(final SwipeRefreshLayout srl) {
        try {
            srl.post(new Runnable() {
                @Override
                public void run() {
                    if (srl!=null){
                        srl.setRefreshing(true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化监听
     */
    protected void initListener() {
    }

    ;

    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int getResourceId();

    /**
     * 初始化控件
     */
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化handler
     */
    protected void initHandler() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getActivityManager().finishActivity(this);
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
    }

}

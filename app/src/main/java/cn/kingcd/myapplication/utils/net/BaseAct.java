package cn.kingcd.myapplication.utils.net;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by cheng on 2016/11/28.
 */
public class BaseAct extends Activity implements NetBroadcastReceiver.NetEvevt {

    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        evevt = this;
        inspectNet();
    }


    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil2.getNetWorkState(BaseAct.this);

        return isNetConnect();

    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
        isNetConnect();
    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == NetUtil2.NETWORK_WIFI) {
            return true;
        } else if (netMobile == NetUtil2.NETWORK_MOBILE) {
            return true;
        } else if (netMobile == NetUtil2.NETWORK_NONE) {
            return false;

        }
        return false;
    }
}

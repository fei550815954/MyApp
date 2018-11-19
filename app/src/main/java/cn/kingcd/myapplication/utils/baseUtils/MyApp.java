package cn.kingcd.myapplication.utils.baseUtils;

import android.app.Application;
import android.content.Context;

/**
 * @author lilingfei
 */

public class MyApp extends Application {
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MyIntentService.start(this);
    }


    public static Context getInstance() {
        return instance;
    }



}

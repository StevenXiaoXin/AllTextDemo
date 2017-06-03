package com.liuzhuang.alltextdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by liuzhuang on 2017/5/28.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        x.Ext.init(this);

    }

    public static MyApplication getApplication() {

        return myApplication;
    }

}

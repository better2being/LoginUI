package com.example.shenxiaoming.loginui.apps;

import android.app.Application;

import org.xutils.x;

/**
 * Created by SHEN XIAOMING on 2016/8/23.
 */
public class MyXutils extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化xutils线程池
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}

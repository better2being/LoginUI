package com.example.shenxiaoming.loginui.activity;

import android.content.Context;
import android.os.Bundle;

import com.example.shenxiaoming.loginui.R;

/**
 * Created by SHEN XIAOMING on 2016/8/22.
 */
public class UserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        // 初始化view
        initView(this);
    }

    /**
     * 初始化view
     * @param context
     */
    private void initView(Context context) {

    }
}

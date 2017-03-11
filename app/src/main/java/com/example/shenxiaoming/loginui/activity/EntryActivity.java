package com.example.shenxiaoming.loginui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shenxiaoming.loginui.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 应用进入介绍界面
 * Created by SHEN XIAOMING on 2016/8/22.
 */
public class EntryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(EntryActivity.this, LoginActivity.class));
                EntryActivity.this.finish();
            }
        };
        timer.schedule(task, 100); // 3秒后跳转
    }
}

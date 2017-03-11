package com.example.shenxiaoming.loginui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by SHEN XIAOMING on 2016/8/21.
 */
public class UnderLineTv extends TextView {

    public UnderLineTv(Context context) {
        super(context);
    }

    public UnderLineTv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnderLineTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
        getPaint().setAntiAlias(true);  // 抗锯齿
        super.onDraw(canvas);
    }
}

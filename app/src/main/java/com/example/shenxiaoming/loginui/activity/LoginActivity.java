package com.example.shenxiaoming.loginui.activity;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shenxiaoming.loginui.R;
import com.example.shenxiaoming.loginui.util.ScreenUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.bmob.v3.Bmob;

public class LoginActivity extends BaseActivity{

    private final static String TAG = "LoginActivity";

    private EditText idEt;
    private EditText passwordEt;

    private ImageView userIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        // 初始化BmobSDK
        Bmob.initialize(this, "cb707628c0f0e87ae579548272368e3b");
        // 初始化view
        initView(this);
    }

    /**
     * 初始化view
     */
    private void initView(Context context) {
        idEt = (EditText) findViewById(R.id.login_id);
        passwordEt = (EditText) findViewById(R.id.login_password);
        userIv = (ImageView) findViewById(R.id.user_iv);
        // 属性动画
        float translationY =
                - (ScreenUtil.getScreenSize(context).heightPixels - (260  - 80) * ScreenUtil.getDeviceDensity(context)) / 2;
        ObjectAnimator animator = ObjectAnimator.ofFloat(userIv,
                "translationY", translationY, 0);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

        // 点击屏幕其它地方使EditView失去焦点
        final RelativeLayout login_at = (RelativeLayout) findViewById(R.id.login_activity);
        login_at.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                login_at.setFocusable(true);
                login_at.requestFocus();
                return false;
            }
        });
    }

    public void onLogin(View view) {
        String username = idEt.getText().toString();
        String password = passwordEt.getText().toString();
        // 账号密码不能为空
        if (username.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.id_empty, Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.password_empty, Toast.LENGTH_LONG).show();
            return;
        }

        // 登录中dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("登录中...");
        progressDialog.setCancelable(true);
        // 登录取消
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        progressDialog.dismiss();
                    }
                });
        progressDialog.show();

        // 创建网络访问的url地址栏
        String url = "http://192.168.173.1:8080/ServeNew/loginCheck";
        RequestParams rp = new RequestParams(url);
        // 封装该rp对象的请求参数
        rp.addBodyParameter("username", username);
        rp.addBodyParameter("password", password);
        // xutils post提交
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // 登录dialog清除
                progressDialog.dismiss();
                if ("ok".equals(result)) {
                    // 传进的内部类的变量需为final
                    Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, UserActivity.class));
                    // 结束登录界面
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, R.string.password_wrong, Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "登录出错", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void onSignUp(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    public void onForget(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

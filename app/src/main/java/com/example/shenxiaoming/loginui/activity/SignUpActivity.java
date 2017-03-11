package com.example.shenxiaoming.loginui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shenxiaoming.loginui.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by SHEN XIAOMING on 2016/6/26.
 */
public class SignUpActivity extends BaseActivity {

    private EditText idEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        idEt = (EditText) findViewById(R.id.signup_id);
        passwordEt = (EditText) findViewById(R.id.signup_password);
    }

    public void onSignUp(View view){
        String username = idEt.getText().toString();
        String password = passwordEt.getText().toString();
        // 账号密码不能为空
        if (username.equals("")) {
            Toast.makeText(SignUpActivity.this, R.string.id_empty, Toast.LENGTH_LONG ).show();
            return;
        }
        if (username.length() != 11) { // 手机号为11位
            Toast.makeText(SignUpActivity.this, R.string.tel_wrong, Toast.LENGTH_LONG ).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(SignUpActivity.this, R.string.password_empty, Toast.LENGTH_LONG).show();
            return;
        }

        // 注册中dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("注册中...");
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

        // 创建网络访问的url地址栏    192.168.173.1
        String url = "http://192.168.173.1:8080/ServeNew/RegisterCheck";
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
                    Toast.makeText(SignUpActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                } else {
                    switch (result) {
                        case "repetition":

                            Toast.makeText(SignUpActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(SignUpActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, "注册出错", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}

package com.alankin.mylearn;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alankin.hihttp.HttpClient;
import com.alankin.hihttp.Request;
import com.alankin.hihttp.Utils;
import com.alankin.mylibrary.AlanKinUtil;
import com.alankin.mylibrary.BindView;
import com.alankin.mylibrary.ContentView;
import com.alankin.mylibrary.OnClick;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @BindView(value = R.id.tv_hello)
    TextView tv_hello;
    @BindView(value = R.id.btn_hello)
    TextView btn_hello;
    @BindView(value = R.id.btn_hello1)
    TextView btn_hello1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlanKinUtil.bind(this);
    }

    @OnClick(R.id.tv_hello)
    public void onClick(View view) {
        tv_hello.setText("点击了我！绑定事件成功");
    }
/*
    @OnClick(R.id.btn_hello)
    public void onClickBtn(View view) {
        btn_hello.setText("点击了btn_hello！绑定事件成功");

    }*/

    public void lala(View view) {
        Handler handler = new Handler();
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .Url("http://139.224.29.103/doc/user.html")
                .build();
        HttpClient.Builder builder1 = new HttpClient.Builder();
        final HttpClient httpClient = builder1
                .setRequest(request)
                .setConnectTimeOut(60 * 1000)
                .setReadTimeOut(60 * 1000)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final byte[] excute = httpClient.newCall().excute();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.log(excute.length + "");
                        Utils.log(new String(excute));
                    }
                });
            }
        }).start();

    }

    @OnClick(R.id.btn_hello1)
    public void onClickBtn1(View view) {
        btn_hello1.setText("点击了btn_hello1！绑定事件成功");
    }
}

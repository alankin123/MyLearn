package com.alankin.mylearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

    @OnClick(R.id.btn_hello)
    public void onClickBtn(View view) {
        btn_hello.setText("点击了btn_hello！绑定事件成功");
    }

    @OnClick(R.id.btn_hello1)
    public void onClickBtn1(View view) {
        btn_hello1.setText("点击了btn_hello1！绑定事件成功");
    }
}

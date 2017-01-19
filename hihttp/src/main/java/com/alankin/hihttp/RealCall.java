package com.alankin.hihttp;

import java.io.UnsupportedEncodingException;

/**
 * Created by alankin on 2017/1/18.
 */
public abstract class RealCall<T> implements Call<T> {
    HttpClient httpClient;
    CallBack<T> callBack;

    public RealCall(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * 推进准备池中
     */
    void push2ReadyPool() {
        ReadyPool.getInstance().add(this);
    }

    @Override
    public void enquee(CallBack<T> callBack) {
        this.callBack = callBack;
        push2ReadyPool();
    }

    @Override
    public byte[] excute() {
        return new Connect(httpClient).connect();


    }

    /**
     * 取消请求
     */
    void cancel() {

    }

    /**
     * 是否取消了请求
     *
     * @return
     */
    boolean isCancel() {
        return false;
    }
}

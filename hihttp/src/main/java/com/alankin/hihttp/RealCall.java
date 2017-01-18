package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public abstract class RealCall<T> implements Call<T> {
    HttpClient httpClient;

    public RealCall(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    void push2ReadyPool() {

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

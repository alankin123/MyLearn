package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 * 请求状态回调接口
 */
public interface CallBack<T> {
    void onError();

    void onSuccess(T t);
}

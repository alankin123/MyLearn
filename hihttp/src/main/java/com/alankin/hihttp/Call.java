package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public interface Call<T> {
    /**
     * 同步请求方法
     */
    byte[] excute();

    /**
     * 异步请求方法
     *
     * @param callBack
     */
    void enquee(CallBack<T> callBack);

}

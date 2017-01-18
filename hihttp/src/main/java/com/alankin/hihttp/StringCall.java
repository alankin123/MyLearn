package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public class StringCall extends RealCall<String>{
    public StringCall(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    public String excute() {
        return null;
    }

    @Override
    public void enquee(CallBack<String> callBack) {

    }
}

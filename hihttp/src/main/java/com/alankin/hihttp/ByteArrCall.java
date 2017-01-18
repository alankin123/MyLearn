package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public class ByteArrCall extends RealCall<byte[]> {
    public ByteArrCall(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    public byte[] excute() {
        return new Connect(httpClient).connect();
    }

    @Override
    public void enquee(CallBack<byte[]> callBack) {
        this.callBack = callBack;
    }
}

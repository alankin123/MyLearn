package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public class ByteArrCall extends RealCall<Byte[]> {
    public ByteArrCall(HttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 同步请求
     *
     * @return
     */
    @Override
    public Response<Byte[]> excute() {
        Response response = new Response();

        return response;
    }

    /**
     * 异步请求
     *
     * @param callBack
     */
    @Override
    public void enquee(CallBack<Response<Byte[]>> callBack) {

    }
}

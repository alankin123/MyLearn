package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public class HttpClient {
    Request request;
    int connectTimeOut;
    int readTimeOut;

    private HttpClient() {
    }

    /**
     * 新建呼叫
     *
     * @return
     */
    Call newCall() {
        return null;
    }

    public static class Builder {
        HttpClient httpClient;
        int connectTimeOut;
        int readTimeOut;
        Request request;

        Builder setConnectTimeOut(int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }

        Builder setReadTimeOut(int readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        void setRequest(Request request) {
            httpClient.request = request;
        }

        HttpClient build() {
            httpClient = new HttpClient();
            httpClient.connectTimeOut = connectTimeOut;
            httpClient.readTimeOut = readTimeOut;
            httpClient.request = request;
            return httpClient;
        }
    }
}

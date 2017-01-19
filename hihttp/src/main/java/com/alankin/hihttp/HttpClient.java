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
    public StringCall newCall() {
        return new StringCall(this);
    }

    public static class Builder {
        HttpClient httpClient;
        int connectTimeOut;
        int readTimeOut;
        Request request;

        public Builder setConnectTimeOut(int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }

        public Builder setReadTimeOut(int readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        public Builder setRequest(Request request) {
            httpClient.request = request;
            return this;
        }

        public HttpClient build() {
            httpClient = new HttpClient();
            httpClient.connectTimeOut = connectTimeOut;
            httpClient.readTimeOut = readTimeOut;
            httpClient.request = request;
            return httpClient;
        }
    }
}

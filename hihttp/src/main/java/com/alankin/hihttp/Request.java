package com.alankin.hihttp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by alankin on 2017/1/18.
 * 请求
 */
public class Request {
    String url;//路径
    Method method;//请求方法
    Map<String, String> headers;
    Params params;//请求参数

    private Request() {
    }

    /**
     * 请求构造者
     */
    public static class Builder {
        String url;
        Method method;
        com.alankin.hihttp.Headers headers;//头信息
        Params params;//请求参数

        Builder Url(String url) throws MalformedURLException {
            Builder.this.url = url;
            return this;
        }

        Builder post() {
            this.method = Method.POST;
            return this;
        }

        Builder get() {
            this.method = Method.GET;
            return this;
        }

        Builder put() {
            this.method = Method.PUT;
            return this;
        }

        Builder delete() {
            this.method = Method.DELETE;
            return this;
        }

        Builder head(String key, String value) {
            if (headers == null) {
                headers = new com.alankin.hihttp.Headers();
            } else {
                headers.clear();
            }
            headers.put(key, value);
            return this;
        }

        Builder headers(com.alankin.hihttp.Headers headers) {
            if (headers == null) {
                return this;
            }
            this.headers = headers;
            return this;
        }

        Builder Params(Params params) {
            if (params == null) {
                return this;
            }
            this.params = params;
            return this;
        }

        Request build() {
            Request request = new Request();
            request.url = url;
            request.method = method;
            request.headers = headers;
            request.params = params;
            return request;
        }
    }
}

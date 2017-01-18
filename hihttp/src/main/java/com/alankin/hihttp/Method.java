package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 * 请求方法枚举
 */
public enum Method {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE");

    public String getMethod() {
        return method;
    }

    private String method;

    Method(String method) {
        this.method = method;
    }
}

package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 */
public class HttpExcutorPool {
    private static HttpExcutorPool singleton;

    private HttpExcutorPool() {
    }

    public static HttpExcutorPool getInstance() {
        if (singleton == null) {
            synchronized (HttpExcutorPool.class) {
                if (singleton == null) {
                    singleton = new HttpExcutorPool();
                }
            }
        }
        return singleton;
    }
}

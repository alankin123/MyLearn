package com.alankin.hihttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alankin on 2017/1/18.
 */
public class HttpExcutorPool {
    private static HttpExcutorPool singleton;
    private final static int NUM = 5;
    private final ExecutorService executorService;

    private HttpExcutorPool() {
        executorService = Executors.newFixedThreadPool(NUM);
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

    /**
     * 将请求添加进线程池
     *
     * @param call
     */
    public void add(Call call) {

    }

    /**
     * 将请求添加进线程池
     *
     * @return
     */
    public boolean isFull() {
        return false;
    }
}

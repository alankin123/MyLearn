package com.alankin.hihttp;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alankin on 2017/1/18.
 * 线程池
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
    public void add(final RealCall call) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final Object data = call.excute();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (call.callBack != null) {
                            call.callBack.onSuccess(data);
                        }
                    }
                });
            }
        });
    }
}

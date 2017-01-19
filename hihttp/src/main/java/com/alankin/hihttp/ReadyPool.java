package com.alankin.hihttp;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by alankin on 2017/1/18.
 * 准备池
 */
public class ReadyPool {
    private static ReadyPool singleton;
    private HttpExcutorPool excutorPool;//线程池
    private LinkedBlockingQueue<RealCall> queue;//阻塞队列池
    private ExecutorService executorService;
    private boolean isLoopOn = false;//循环是否开启

    private ReadyPool() {
        excutorPool = HttpExcutorPool.getInstance();
        queue = new LinkedBlockingQueue();
        executorService = Executors.newSingleThreadExecutor();
    }

    public static ReadyPool getInstance() {
        if (singleton == null) {
            synchronized (ReadyPool.class) {
                if (singleton == null) {
                    singleton = new ReadyPool();
                }
            }
        }
        return singleton;
    }

    /**
     * 将请求添加进准备池
     *
     * @param call
     */
    public synchronized void add(RealCall call) {
        try {
            queue.put(call);
            //当队列不为空，且循环未开启则启动循环服务
            if (!queue.isEmpty() && !isLoopOn) {
                startPushService();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环放进线程池中
     */
    private synchronized void startPushService() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (RealCall realCall : queue) {
                    excutorPool.add(realCall);
                }
                isLoopOn = false;
            }
        });
        isLoopOn = true;
    }

}

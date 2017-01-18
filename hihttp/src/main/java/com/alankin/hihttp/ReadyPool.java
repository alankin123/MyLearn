package com.alankin.hihttp;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by alankin on 2017/1/18.
 * 准备池
 */
public class ReadyPool {
    private static ReadyPool singleton;
    private HttpExcutorPool excutorPool;//线程池
    private LinkedBlockingQueue queue;//阻塞队列池
    private Thread pushService;//轮训推进服务

    private ReadyPool() {
        excutorPool = HttpExcutorPool.getInstance();
        queue = new LinkedBlockingQueue();
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
    public synchronized void add(Call call) {
        try {
            queue.put(call);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 放进线程池中
     */
    private void push2ExcutorPool() {
    }

    /**
     * （懒汉）启动推进服务
     */
    private synchronized void startPushService() {
        if (pushService != null) {
            pushService = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Iterator iterator = queue.iterator();
                        while(iterator.hasNext()){
                        }
                    }
                }
            });
        }
    }
}

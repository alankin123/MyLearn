package com.alankin.hihttp;

/**
 * Created by alankin on 2017/1/18.
 * 准备池
 */
public class ReadyPool {
    private static ReadyPool singleton;
    private HttpExcutorPool excutorPool;//线程池

    private ReadyPool() {
        excutorPool = HttpExcutorPool.getInstance();
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
    private void add(Call call) {

    }
}

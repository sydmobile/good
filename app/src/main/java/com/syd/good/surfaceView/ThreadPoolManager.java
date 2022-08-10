package com.syd.good.surfaceView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/1 16:49
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ThreadPoolManager {
    private static ThreadPoolManager mThreadPoolManager;
    private ExecutorService mFixedThreadPool;
    private ScheduledExecutorService mScheduledThreadPool;

    private ThreadPoolManager() {

    }

    public static ThreadPoolManager getInstance() {
        if (mThreadPoolManager == null) {
            synchronized (ThreadPoolManager.class) {
                if (mThreadPoolManager == null) {
                    mThreadPoolManager = new ThreadPoolManager();
                }
            }
        }
        return mThreadPoolManager;
    }

    public ExecutorService getFixedThreadPool() {
        if (mFixedThreadPool == null) {
            synchronized (ThreadPoolManager.class) {
                if (mFixedThreadPool == null) {
                    ThreadFactory factory = Executors.defaultThreadFactory();
                    mFixedThreadPool = new ThreadPoolExecutor(2, 2, 0,
                            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), factory);
                }
            }
        }
        return mFixedThreadPool;
    }


    public ScheduledExecutorService getScheduledThreadPool(){
        if (mScheduledThreadPool == null){
            synchronized (ThreadPoolManager.class){
                if (mScheduledThreadPool == null){
                    ThreadFactory factory = Executors.defaultThreadFactory();
                    mScheduledThreadPool = new ScheduledThreadPoolExecutor(1,factory);
                }
            }
        }else {
            if (mScheduledThreadPool.isShutdown()){
                ThreadFactory factory = Executors.defaultThreadFactory();
                mScheduledThreadPool = new ScheduledThreadPoolExecutor(1,factory);
            }
        }
        return mScheduledThreadPool;
    }


}

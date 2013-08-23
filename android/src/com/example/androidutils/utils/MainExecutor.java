
package com.example.bustest.cpu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainExecutor {
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    private MainExecutor() {
    }

    private static MainExecutor sInstance;

    public static synchronized MainExecutor getInstance() {
        return sInstance == null ? sInstance = new MainExecutor() : sInstance;
    }

    public <T> void execute (Task<T> task, CallBack<T> callBack) {
        mExecutorService.execute(new RunnableDecorator<T>(task, callBack));
    }
    
    public interface CallBack <T>{
        void fullfilled(T o);
    }
    
    public interface Task <T> {
        T fullfill();
    }

    private class RunnableDecorator <T> implements Runnable {
        private Task<T> mRunnable;
        private CallBack<T> mCallback;

        public RunnableDecorator(Task<T> runnable, CallBack<T> callback) {
            mRunnable = runnable;
            mCallback = callback;
        }

        @Override
        public void run() {
            T o = mRunnable.fullfill();
            if(o != null)
                mCallback.fullfilled(o);
        }
    }
}

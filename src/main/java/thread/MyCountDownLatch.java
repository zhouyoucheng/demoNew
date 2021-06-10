package thread;

import jodd.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by supaur on 2020/12/11 17:02
 */
public class MyCountDownLatch {

    static ExecutorService executorService = new ThreadPoolExecutor(
            8,
            10240,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            ThreadFactoryBuilder.create().get(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) {
        int num = 10000;
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(num);
        for (int i = 0; i < num; i++) {
            int a = i;
            executorService.submit(() -> {
                System.out.println(a);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
            });
        }
        myCountDownLatch.await();
        System.out.println("wo laile   " + 111);
    }

    private volatile AtomicInteger status = new AtomicInteger(0);

    private volatile Thread thread = null;

    public MyCountDownLatch(int num) {
        status.set(num);
    }

    public void countDown(){
        int i = status.decrementAndGet();
        if (i == 1) {
            LockSupport.unpark(thread);
        }
    }

    public void await() {
        int i = status.get();
        if (i == 0) {
            return;
        }
        thread = Thread.currentThread();
        LockSupport.park();
    }
}

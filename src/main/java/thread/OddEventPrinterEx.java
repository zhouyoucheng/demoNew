package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEventPrinterEx {
    volatile Long time;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final int limit;
    private volatile int count;

    public OddEventPrinterEx(int limit, int count) {
        this.count = count;
        this.limit = limit;
    }

    public void print() {
        lock.lock();
        try {
            while (count < limit) {
                if (count == 0) {
                    time = System.currentTimeMillis();
                }
                if (count == limit) {
                    time = System.currentTimeMillis() - time;
                }
                System.out.println(Thread.currentThread().getName() + "---" + count++);
                condition.signalAll();
                try {
                    condition.await();
                } catch (Exception e) {

                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        OddEventPrinterEx oddEventPrinterEx = new OddEventPrinterEx(10, 0);

        new Thread(() -> {
            try {
                oddEventPrinterEx.print();
            } finally {
                countDownLatch.countDown();
            }

        }, "thread-1").start();

        new Thread(() -> {
            try {
                oddEventPrinterEx.print();
            } finally {
                countDownLatch.countDown();
            }
        }, "thread-2").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("0000000000000000000000000000");
        System.out.println(oddEventPrinterEx.time);
    }
}

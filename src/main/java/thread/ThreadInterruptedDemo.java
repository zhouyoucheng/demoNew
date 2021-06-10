package thread;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ThreadInterruptedDemo extends Thread{
    private ThreadInterruptedDemo other;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        sleepThenInterrupt();
    }

    /**
     * 线程的interrupt只是把线程中断标志位修改为ture,自己本不中断线程
     * 在线程的标志位是true的时候，调用sleep,wait,join方法会抛出异常，
     * 可以catch然后自己决定怎么做
     */
    private static void testInterrupt() {
        Thread thread = Thread.currentThread();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    /**
     * 先sleep，然后调用打断方法，抛出异常
     * @throws InterruptedException
     */
    private static void sleepThenInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("son sout");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(500);
        System.out.println("main sout");
        Thread.sleep(3000);
//        thread.interrupt();
    }

    /**
     * 先调用打断方法打断当前线程，再调用Thread的interrupted方法清除打断标志位置，再调用sleep方法则不会抛出异常
     * @throws InterruptedException
     */
    private static void interruptThenClearThenSleep() throws InterruptedException {
        Thread thread = Thread.currentThread();
        thread.interrupt();
        // 该方法获取当前线程的打断标志状态，再把打断标记设置为false,既没有打断
        Thread.interrupted();
        Thread.sleep(1);
    }

    /**
     * 先打断再sleep,抛出异常
     * @throws InterruptedException
     */
    private static void interruptThenSleep() throws InterruptedException {
        Thread thread = Thread.currentThread();
        thread.interrupt();
        Thread.sleep(1);
    }
}

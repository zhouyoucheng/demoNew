package thread;

import java.util.concurrent.CountDownLatch;
/**
 * new CountDownLatch(int n)声明一个n个线程的构造函数
 * countDown()调用一次，线程数减少1，当线程数为0时，执行await()方法后面的任务。
 * @author wb-zyc501131
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                   System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");

               } finally {
                    latch.countDown();
               }
            }
        }.start();

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
               } finally {
                    latch.countDown();
               }
            }
        }.start();

        try {
            System.out.println("等待2个子线程执行完毕...");
           latch.await();
           System.out.println("2个子线程已经执行完毕");
           System.out.println("继续执行主线程");
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
	
}

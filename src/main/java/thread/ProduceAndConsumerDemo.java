package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProduceAndConsumerDemo {


    public static BlockingQueue threadList =  new LinkedBlockingQueue<Object>(3);

    public static void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1111);
        });
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2222);
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(3333);
        });

    }


    public static void main(String[] args) {
//        try {
//            test();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < 10; i++) {
            int a = i;
            new Thread(() -> {
                ProduceAndConsumerDemo.put(a);
            }).start();;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                ProduceAndConsumerDemo.get();
            }).start();
        }


    }

    public static BlockingQueue list =  new LinkedBlockingQueue<Object>(3);

    public static void put(Object o) {
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.put(o);
            System.out.println("put:--" + Thread.currentThread().getName() + "size:" + ProduceAndConsumerDemo.list.size());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Object get() {
        try {
            Object take = list.take();
            System.out.println("get:--" + Thread.currentThread().getName() + "size:" + ProduceAndConsumerDemo.list.size());
            return take;
        } catch (InterruptedException e) {
            System.out.println(e);
            return null;
        }
    }

}

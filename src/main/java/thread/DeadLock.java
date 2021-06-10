package thread;

import lombok.Setter;

/**
 * 用静态资源来模拟共享资源
 */

public class DeadLock extends Thread{

    public static Object source1 = new Object();
    public static Object source2 = new Object();
    @Setter
    private boolean flag = false;

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        DeadLock deadLock1 = new DeadLock();
        deadLock1.setFlag(true);
        deadLock.start();
        deadLock1.start();
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (source1) {
                System.out.println("lock source1" + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                synchronized (source2) {
                    System.out.println("lock source1" + Thread.currentThread().getName());
                }
            }
        }  else {
            synchronized (source2) {
                System.out.println("lock source2" + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                synchronized (source1) {
                    System.out.println("lock source1" + Thread.currentThread().getName());
                }
            }
        }
    }
}

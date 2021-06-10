package thread;

/**
 * Thread的sleep方法不会释放已经持有的锁，object的wait方法是释放锁
 */
public class ObjectMethodTest {
    static Object  lock = new Object();


    public static void main(String[] args) {
//        testSleep();
        testWait();
    }
    public static void testWait(){
        new Thread(()->{
            synchronized (lock){
                try {
                    System.out.println("A休眠10秒放弃锁");
                    lock.wait(2000);
                    System.out.println("A休眠10秒醒来");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(()->{

            synchronized (lock){
                System.out.println("B休眠10秒放弃锁");
                try {
                    lock.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B休眠10秒醒来");

            }

        }).start();


    }

    public static void testSleep(){
        new Thread(()->{
            synchronized (lock){
                try {
                    System.out.println("A休眠10秒不放弃锁");
                    Thread.sleep(2000);
                    System.out.println("A休眠10秒醒来");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(()->{

            synchronized (lock){
                System.out.println("B休眠10秒不放弃锁");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B休眠10秒醒来");

            }

        }).start();


    }
}

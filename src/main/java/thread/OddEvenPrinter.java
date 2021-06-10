package thread;

public class OddEvenPrinter {
    private final Object monitor = new Object();
    private final int limit;
    private volatile int count;
    private volatile Long time;

    public OddEvenPrinter(int limit, int count) {
        this.count = count;
        this.limit = limit;
    }

    public void print() {
        synchronized (monitor) {
            while (count < limit) {
                try {
                    if (count == limit) {
                        time = System.currentTimeMillis() - time;
                    }
                    if (count == 0) {
                        time = System.currentTimeMillis();
                    }
                    System.out.println(Thread.currentThread().getName() + "---" + count++);
                    monitor.notifyAll();
                    monitor.wait();
                } catch (Exception e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter(100, 0);
        Thread thread1 = new Thread(printer :: print, "Thread-1");
        Thread thread2 = new Thread(printer :: print, "Thread-2");
        thread1.start();
        thread2.start();

        OddEventPrinterEx oddEventPrinterEx = new OddEventPrinterEx(10000, 0);
        Thread thread3 = new Thread(oddEventPrinterEx :: print, "thread-3");
        Thread thread4 = new Thread(oddEventPrinterEx :: print, "thread-4");
        thread1.start();
        thread2.start();
        System.out.println(oddEventPrinterEx.time);
    }
}

package designModule;

public class Singleton {

    /**
     * 声明为volatile
     * 1.保证可见性
     * 2.防止指令重排序
     */
    private static volatile Singleton instance;

    /**
     * 私有化构造方法
     */
    private Singleton(){}


    /**
     * 锁不加在方法上，减少阻塞
     * instance必须声明为volatile的，防止指令重排序
     *                     instance = new Singleton();可以拆解成三个步骤
     *                     1. memory = allocated()  分配空间
     *                     2. initInstance(memory)  初始化memory
     *                     3. instance = memory     把instance指向memory
     *                     实际上3和2是没有依赖关系的，因此3是可能在2之前执行的。假如一种情况：
     *                     A线上执行到这里时时先执行的3，B线上这时候进来了并抢走了时间片执行，在B线程的视角，第一个检查条件if
     *                     判断就进不去，直接返回了还没有初始化完全的instance
     * @return
     */
    public static Singleton getSingleton(){
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}

package thread;

import jodd.util.concurrent.ThreadFactoryBuilder;

import java.lang.reflect.Field;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池执行原理
 * 1.核心线程未满，任务来了先创建线程去处理。
 * 2.核心线程满了，任务来了先放队列。
 * 3.队列满了，只要最大线程数量还没满，创建新的线程来处理任务。
 * 4.线程池能接受的最大任务量为队列的容量加上最大线程池数量
 */
public class ThreadPoolExecutorTest{
	static ThreadPoolExecutor executorService = new ThreadPoolExecutor(
			2,
			50,
			0,
			TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(100),
			ThreadFactoryBuilder.create().get(),
			new ThreadPoolExecutor.AbortPolicy()
	);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			try {
				int a = i;
				executorService.submit(() -> {
					try {
						Thread.sleep(1000000);
						System.out.println(a);
					} catch (InterruptedException e) {
						System.out.println(a);
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				System.out.println(i);
				System.out.println(i + "------------" + e);
				try {
					Field workQueue = executorService.getClass().getDeclaredField("workQueue");
					//Field workQueue = ThreadPoolExecutor.class.getDeclaredField("workQueue");
					workQueue.setAccessible(true);
					try {
						LinkedBlockingQueue o = (LinkedBlockingQueue)workQueue.get(executorService);
						System.out.println(o.size());
					} catch (IllegalAccessException illegalAccessException) {
						illegalAccessException.printStackTrace();
					}
				} catch (NoSuchFieldException noSuchFieldException) {
					noSuchFieldException.printStackTrace();
				}
				break;
			}
		}




	}


}

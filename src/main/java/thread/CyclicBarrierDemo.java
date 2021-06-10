package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * see ->1.CyclicBarrier可以让指定的 一些线程先执行cyclicBarrier.await()之前的任务，所有线程执行
 * see ->完之前的逻辑再处理后面的逻辑
 * see ->2.new CyclicBarrier(int n, new Runnable())会把new Runnable()放在cyclicBarrier.await()
 * see ->之后，后续任务之前执行
 * see ->3.CyclicBarrier可以重用
 *
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		int n = 5;
		// 构造函数里面的任务执行在cyclicBarrier.await()之后，后面任务之前，执行一次
		CyclicBarrier barrier = new CyclicBarrier(n, () -> System.out.println(1111));
		for (int i = 0; i < 10; i++) {
			new Write(barrier).start();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class Write extends Thread {
		private CyclicBarrier cyclicBarrier;
		public Write() {}

        public Write(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			 System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
	            try {
	                Thread.sleep(2000);      //以睡眠来模拟写入数据操作
	                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
	                Thread.sleep(2000);
	                cyclicBarrier.await();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }catch(BrokenBarrierException e){
	                e.printStackTrace();
	            }
	            System.out.println("所有线程写入完毕，继续处理其他任务...");
		}
	}
}

package thread;

import java.util.concurrent.Semaphore;
/**
 * see->Semaphore(int permits)参数permits表示许可数目，即同时可以允许多少线程进行访问
 * see->Semaphore(int permits, boolean fair)fair表示是否是公平的，即等待时间越久的越先获取许可
 * see->tryAcquire()尝试获取一个许可，若获取成功
 * see->semaphore.release()释放一个许可
 * see->tryAcquire(int permits)尝试获取permits个许可，若获取成功
 * see->release(int permits)释放permits个许可
 * see->release late tryAcquire first
 */
public class SemaphoreDemo {
	 public static void main(String[] args) {
	        int N = 8;            //工人数
	        Semaphore semaphore = new Semaphore(5); //机器数目
	        for(int i=0;i<N;i++){
				new Worker(i,semaphore).start();
			}

		 System.out.println("end");

	    }

	    static class Worker extends Thread{
	        private int num;
	        private Semaphore semaphore;
	        public Worker(int num,Semaphore semaphore){
	            this.num = num;
	            this.semaphore = semaphore;
	        }

	        @Override
	        public void run() {
	            try {
	                semaphore.acquire();
	                System.out.println("工人"+this.num+"占用一个机器在生产...");
	                Thread.sleep(2000);
	                System.out.println("工人"+this.num+"释放出机器");
	                semaphore.release();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}

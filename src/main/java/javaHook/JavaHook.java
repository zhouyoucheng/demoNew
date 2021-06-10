package javaHook;

public class JavaHook extends Thread{
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new JavaHook());
		System.out.println(runtime.availableProcessors());
		for (int i = 0; i <10; i++) {
			System.out.println(i);
			if (i== 4) {
				System.exit(0);
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		System.out.println("hook shutDown");
	}
}

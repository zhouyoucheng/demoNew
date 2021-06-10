package thread;

public class InheritableThreadLocalTest {
	public static void main(String[] args) {
		ThreadLocal<String> threadLocal=new InheritableThreadLocal<String>();
		threadLocal.set("123");
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String string = threadLocal.get();//NULL
				System.out.println(string);
				threadLocal.set("456");
				System.out.println("---"+ threadLocal.get());
			}
		});
		thread.start();
		String string1 = threadLocal.get();//NULL
		System.out.println(string1);
	}
}

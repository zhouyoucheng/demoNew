package listener;

/**
 * 事件源：机器人
 * @author wb-zyc501131
 *
 */
public class Robot {

	private RobotListener listener;

	/**
	 * 注册机器人监听器
	 */
	public void registerListener(RobotListener listener) {
		this.listener = listener;
	}

	public void working() {
		if (listener != null) {
			Even even = new Even(this);
			this.listener.working(even);
		}
		System.out.println("Robot.working()");
	}
	
	public void danceing() {
		if (listener != null) {
			Even even = new Even(this);
			listener.danceing(even);
		}
		System.out.println("Robot.danceing()");
	}
}

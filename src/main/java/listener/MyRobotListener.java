package listener;

public class MyRobotListener implements RobotListener{

	public static void main(String[] args) {
		Robot robot = new Robot();
		robot.registerListener(new MyRobotListener());
		robot.danceing();
		robot.working();
	}

	@Override
	public void danceing(Even even) {
		System.out.println(even.getE());
		System.out.println("机器人工作提示：请看管好的你机器人，防止它偷懒！");
	}

	@Override
	public void working(Even even) {
		System.out.println(even.getE());
		System.out.println("机器人跳舞提示：机器人跳舞动作优美，请不要走神哦！");
	}
}

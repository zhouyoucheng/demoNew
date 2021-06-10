package designModule;
/**
 * 策略模式
 * @author wb-zyc501131
 *
 */
public class StrategyDemo {
	public static void main(String[] args) {
		// 测试策略模式
		Strategy strategy = new Strategy(new Start());
		strategy.doAction();
		strategy = new Strategy(new Stop());
		strategy.doAction();
	}
}

interface Action {
	void doAction();
}

class Strategy {
	private Action action;
	
	public Strategy(Action action) {
		this.action = action;
	}
	public void doAction() {
		action.doAction();
	}
}

class Stop implements Action {
	@Override
	public void doAction() {
		System.out.println("Stop.doAction(2)");
	}
}

class Start implements Action {
	@Override
	public void doAction() {
		System.out.println("Start.doAction(1)");
	}
}


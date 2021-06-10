package designModule;

/**
 * 状态模式
 * @author wb-zyc501131
 * @see Context环境角色具有两个不成文的约束：一是有几个状态对象就声明几个静态常量；
 * @see 二是具有状态抽象角色定义的所有行为，具体执行使用委托的方式
 * @see 每个具体状态角色所要做的事情就是处理本状态下所对应的动作
 * @see 不属于本状态处理的行为，就会切换状态并把该行为委托给负责该行为的状态。 
 * @see 各个状态可以相互切换
 */
public class StateDemo {
	public static void main(String[] args) {
		Context context = new Context();
		context.setLinkState(Context.CARRYING_STATE);
		context.carry();
		context.ignite();
		context.put();
		context.goaway();
		context.ignite();
	}
}

abstract class LinkState {
	// 注意用的是protected
	protected Context context;

	public void setContext(Context context) {
		this.context = context;
	}
	//扛炸药包
	public abstract void carry();
	//放置炸药包
	public abstract void put();
	//跑路
	public abstract void goaway();
	//点燃炸药包
	public abstract void ignite();
}

class CarryingState extends LinkState{
	@Override
	public void carry() {
		System.out.println("小Y:报告排长，我已跑到炮楼底下");
	}

	@Override
	public void put() {
		context.setLinkState(Context.PUTTINGS_TATE);
		context.getLinkState().put();
	}
	@Override
	public void goaway() {
		System.out.println("小Y:还没放置炸药成功，撤离失败");
	}
	@Override
	public void ignite() {
		System.out.println("小Y:还没放置炸药成功，引爆失败");
	}
}

class PuttingState extends LinkState{
	@Override
	public void carry() {
		System.out.println("小Y:还在敌方区，无法再扛炸药包");
	}

	@Override
	public void put() {
		System.out.println("小Y:好险，成功把放置炸药包");
	}
	@Override
	public void goaway() {
		super.context.setLinkState(Context.GOAWAYING_STATE);
		super.context.getLinkState().goaway();
	}
	@Override
	public void ignite() {
		System.out.println("小Y:还没逃离敌方区，无法引爆炸药");
	}
}

class GoAwayingState extends LinkState{
	@Override
	public void carry() {
		System.out.println("小Y:已经逃离，无法再扛炸药包");
	}

	@Override
	public void put() {
		System.out.println("小Y:已经逃离，无法进行炸药包放置");
	}
	@Override
	public void goaway() {
		System.out.println("小Y:呼~~，终于捡回条小命");
	}
	@Override
	public void ignite() {
		super.context.setLinkState(Context.IGNITING_STATE);
		super.context.getLinkState().ignite();
	}
}

class IgnitingState extends LinkState{
	@Override
	public void carry() {
		System.out.println("炮楼没彻底倒下，小Y继续扛炸药");
		super.context.setLinkState(Context.CARRYING_STATE);
		super.context.getLinkState().carry();
	}

	@Override
	public void put() {
		System.out.println("小Y:炸药已引爆，无须进行炸药包放置");
	}
	@Override
	public void goaway() {
		System.out.println("小Y:炸药已引爆，早已撤离");
	}
	@Override
	public void ignite() {
		System.out.println("小Y:炸楼完成，准备加薪晋职");
	}
}

class Context {
	//定义炸楼所有状态
	public final static CarryingState CARRYING_STATE = new CarryingState();
	public final static PuttingState PUTTINGS_TATE = new PuttingState();
	public final static GoAwayingState GOAWAYING_STATE = new GoAwayingState();
	public final static IgnitingState IGNITING_STATE = new IgnitingState();

	private LinkState linkState;

	public LinkState getLinkState() {
		return linkState;
	}

	public void setLinkState(LinkState linkState) {
		this.linkState = linkState;
		// 把当前所处的状态通知到各个实现类中
		this.linkState.setContext(this);
	}

	public void carry(){
		this.linkState.carry();
	}

	public void put(){
		this.linkState.put();
	}

	public void goaway(){
		this.linkState.goaway();
	}

	public void ignite(){
		this.linkState.ignite();
	}

}
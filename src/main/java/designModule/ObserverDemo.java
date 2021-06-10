package designModule;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 观察者模式
 * @author wb-zyc501131
 *
 */
public class ObserverDemo {
	
	public static void main(String[] args) {
		
		WechatServer server = new WechatServer();
		Observer zhang = new User("zhangSan", null);
		Observer li = new User("liSi", null);
		Observer wang = new User("wangWu", null);

		server.registerObserver(zhang);
		server.registerObserver(li);
		server.registerObserver(wang);

		server.setInfomation("PHP是世界上最好用的语言！");
		
		System.out.println("....................");
		
		server.removerObserver(zhang);
		server.setInfomation("JAVA才是");
	}

}

/**
 * 抽象被观察者接口
 * @author wb-zyc501131
 *
 */
interface Observeable {
	void registerObserver(Observer o);
	void removerObserver(Observer o);
	void notifyObserver();	
}

/**
 * 抽象管擦着
 * 定义了一个update()方法，当观察者调用notifyObservers(),观察者的update()被调用：
 * @author wb-zyc50113
 */
interface Observer{
	void update(String message);
}

@Data
class WechatServer implements Observeable {

	private List<Observer> list;
	private String message;
	public WechatServer() {
		list = new ArrayList<Observer>();
	}
	@Override
	public void registerObserver(Observer o) {
		list.add(o);
	}
	@Override
	public void removerObserver(Observer o) {
		list.remove(o);
	}
	@Override
	public void notifyObserver() {
		list.forEach(v -> v.update(message));
	}

	public void setInfomation(String s) {
		this.message = s;
		System.out.println("微信服务更新消息： " + s);
		//消息更新，通知所有观察者
		notifyObserver();
	}
}

@Data
@AllArgsConstructor
class User implements Observer {
	private String name;
	private String age;
	
	@Override
	public void update(String message) {
		System.out.println(name + " 收到推送消息： " + message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
            return other.name == null;
		} else return name.equals(other.name);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	
}
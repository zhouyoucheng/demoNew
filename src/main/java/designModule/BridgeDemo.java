package designModule;

import lombok.Setter;

/**
 * 桥接模式就是把事务和其具体实现分开，使得他们可以各自独立的变化
 * 将抽象与实现解耦，使二者可以独立变化
 * JDBC和DriverManager就是这样，jdbc连接数据库的时候，在各个数据库之间进行切换，基本不需要太多的代码，甚至可以
 * 丝毫不动，原因是jdbc提供各自的实现，用一个叫数据库驱动的程序来桥接就行了
 * @author wb-zyc501131
 *
 * 对于有两个独立变化的维度，使用桥接模式再适合不过了
 * 对于“具体的抽象类”所作的改变，是不会影响到客户
 */
public class BridgeDemo {
	public static void main(String[] args) {
		Color gray = new Gray();
		Shape square = new Square();
		square.setColor(gray);
		square.draw();


		Shape rectange = new Rectangle();
		rectange.setColor(gray);
		rectange.draw();

		square.setColor(gray);
		square.draw();
	}

}

interface Color {
	void bepaint(String shape);
}

/**
 * 桥接模式的关键
 * @author wb-zyc501131
 *
 */
@Setter
abstract class Shape{
	Color color; // implementor
	abstract void draw(); // operation(),该操作里面调用实现类的的方法
}

class CirCle extends Shape {
	@Override
	void draw() {
		color.bepaint("圆形");
	}
}

class Rectangle extends Shape {
	@Override
	void draw() {
		color.bepaint("长方形");
	}
}

class Square extends Shape {
	@Override
	void draw() {
		color.bepaint("正方形");
	}
}

class Gray implements Color {
	@Override
	public void bepaint(String shape) {
		System.out.println("灰色的" + shape);
	}
}

class Black implements Color {
	@Override
	public void bepaint(String shape) {
		System.out.println("黑色的" + shape);
	}
}


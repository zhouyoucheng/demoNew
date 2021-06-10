package designModule;

import lombok.AllArgsConstructor;

/**
 * 1 类适配器   2 对象适配器  3 接口适配器
 * @author wb-zyc501131
 * 接口适配器不同于1，2用在接口定义的方法多，但是我们又不需要实现那么多方法的情况
 * 
 * 适用场景
 * 1.想要使用一个已经存在的类，但是它却不符合现在的接口规范，导致无法访问，这时候创建一个适配器就能间接的去访问这个类中的方法。
 * 2.我们有一个类，想将其设计为可重用的类，我们可以创建适配器来将这个类适配其他没有提供合适接口的类。
 * 3.想要使用接口中的某个方法或者某些方法，但是接口中的有太多方法要实现，可以使用抽象类来实现接口，这个类就是适配器。
 *
 */
public class AdaptDemo {
	public static void main(String[] args) {
		Adapter1 adapter1 = new Adapter1();
		adapter1.isPs2();
		Adapter2 adapter2 = new Adapter2(new Usber());
		adapter2.isPs2();
		Adapter3 adapter3 = new Adapter3();
		adapter3.a();
	}
}

interface Ps2 {
	void isPs2();
}

interface Usb {
	void isUseb();
}

class Usber implements Usb {
	@Override
	public void isUseb() {
		System.out.println("Usber.isUseb()");
	}
}

class Adapter1 extends Usber implements Ps2 {
	@Override
	public void isPs2() {
		 isUseb();
	}
}

@AllArgsConstructor
class Adapter2 implements Ps2 {
  private Usb usb;
  @Override
	public void isPs2() {
	  	usb.isUseb();
	}
}

interface A {
    void a();
    void b();
    void c();
    void d();
    void e();
    void f();
}

abstract class AbstractAdapter implements A {
    public void a(){}
    public void b(){}
    public void c(){}
    public void d(){}
    public void e(){}
    public void f(){}
}

class Adapter3 extends AbstractAdapter {
	@Override
	public void a() {
		System.out.println("Adapter3.a()");
	}	
}



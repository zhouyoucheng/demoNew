package designModule;

import lombok.AllArgsConstructor;

/**
 * 动态的为一个对象添加一些额外的职责，若要扩展一个对象的功能，装饰者提供了比继承更有弹性的方案。
 * @author wb-zyc501131
 *
 */
public class DecoratorDemo {
	public static void main(String[] args){

		Beverage beverage1 = new DarkRoast();
		beverage1 = new Milk(beverage1);
		beverage1 = new Mocha(beverage1);
		System.out.println(beverage1.getDescription() + "$" + beverage1.cost());

		Beverage beverage2 = new HouseBlend();
		beverage2 = new Milk(beverage2);
		beverage2 = new Mocha(beverage2);
		System.out.println(beverage2.getDescription() + "$" + beverage2.cost());
	}
}


abstract class Beverage {
	String description = "未知的咖啡";
	public String getDescription() {
		return description;
	}

	abstract double cost();
}

class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "深培咖啡";
	}

	@Override
	double cost() {
		return 1.0;
	}
}

class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "混合咖啡";
	}

	@Override
	double cost() {
		return 0.89;
	}
}

abstract class ConimentDecorator extends Beverage{
	public abstract String getDescription();
}

@AllArgsConstructor
class Mocha extends ConimentDecorator {
	Beverage beverage;

	@Override
	public String getDescription() {
		return beverage.getDescription() + ",摩卡";
	}

	@Override
	double cost() {
		return beverage.cost() + .20;
	}
}

@AllArgsConstructor
class Milk extends ConimentDecorator {
	Beverage beverage;

	@Override
	double cost() {
		return beverage.cost() + .10;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + " ,牛奶";
	}
}





















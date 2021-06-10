package designModule;

/**
 * @see //责任链模式
 * @author wb-zyc501131
 *
 */
public class ChainDemo {

	public static void main(String[] args) {
		Customer customer = new Customer();
		customer.setPHandle(PriceHandle.createHandle());
		double discount;
		discount = Math.random();
		customer.disCountSale(discount);
	}
}

class Customer {

	private PriceHandle priceHandle;
	public void setPHandle (PriceHandle priceHandle) {
		this.priceHandle = priceHandle;
	}
	public void disCountSale(double discount) {
		priceHandle.discount(discount);
	}
}

abstract class PriceHandle {
	public PriceHandle() {
		System.out.println("ChainDemo.PriceHandle.PriceHandle()");
	}

    protected PriceHandle priceHandle;

	public void setPriceHandle(PriceHandle priceHandle) {
		this.priceHandle = priceHandle;
	}

	public abstract void discount(double discount);

	/**
	 * 这个是该demo的关键
	 * @return
	 */
	public static PriceHandle createHandle() {
		PriceHandle handleMarket = new Market();
		return handleMarket;
	}
}

class Boss extends PriceHandle {
	@Override
	public void discount(double discount) {
		if (discount <0.6) {
			System.out.println("boss处理了请求：" + discount + "可以卖" );
		} else {
			System.out.println("boss拒绝了请求" + discount + "折扣太高了");
		}
	}
}

class Manager extends PriceHandle {
	@Override
	public void discount(double discount) {
		if (discount <0.3) {
			System.out.println("manager处理了请求：" + discount + "可以卖" );
		} else {
			setPriceHandle(new Boss());
			priceHandle.discount(discount);
		}
	}
}

class  Market extends PriceHandle {
	@Override
	public void discount(double discount) {
		if (discount <0.1) {
			System.out.println("market处理了请求：" + discount + "可以卖" );
		} else {
			setPriceHandle(new Manager());
			priceHandle.discount(discount);
		}
	}

}

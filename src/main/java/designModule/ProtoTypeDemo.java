package designModule;
/**
 * @see
 * @author wb-zyc501131
 *
 */
public class ProtoTypeDemo implements Cloneable {
	
	public static void main(String[] args) {
		ProtoTypeDemo protoTypeDemo = new ProtoTypeDemo();
		protoTypeDemo.setAttribute1(1);
		protoTypeDemo.setAttribute2(1);
		protoTypeDemo.setAttribute3(1);
		ProtoTypeDemo protoTypeDemo2 = protoTypeDemo.clone();
		protoTypeDemo2.setAttribute3(3);
		System.out.println(protoTypeDemo);
		System.out.println(protoTypeDemo2);
	}
	private Object attribute1;
	private Object attribute2;
	private Object attribute3;
	
	public Object getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(Object attribute1) {
		this.attribute1 = attribute1;
	}
	public Object getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(Object attribute2) {
		this.attribute2 = attribute2;
	}
	public Object getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(Object attribute3) {
		this.attribute3 = attribute3;
	}
	
	@Override
	public String toString() {
		return "ProtoTypeDemo [attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", attribute3=" + attribute3
				+ "]";
	}
	
	@Override
		protected ProtoTypeDemo clone()  {
			// TODO Auto-generated method stub
			try {
				return (ProtoTypeDemo)super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}

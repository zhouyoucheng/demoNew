package designModule;

/**
 * facade模式
 * @author wb-zyc501131
 *
 */
public class FacadeDemo {
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.start();
		computer.shutDown();
	}

}

class Computer implements Component{
	private CPU cpu;
	private Memory memory;
	private Disk disk;
	{
		this.cpu = new CPU();
		this.disk = new Disk();
		this.memory = new Memory();
	}
	@Override
	public void start() {
		cpu.start();
		disk.start();
        memory.start();
		System.out.println("开机完成");
	}

	@Override
	public void shutDown() {
		cpu.shutDown();
		disk.shutDown();
		memory.shutDown();
		System.out.println("Computer.shutDown()");
	}

}
interface Component {
	void start();
	void shutDown();
}
class CPU implements Component{
	public void start() {
		System.out.println("CPU.start()");
	}

	@Override
	public void shutDown() {
		System.out.println("CPU.shutDown()");
	}
}

class Disk implements Component {
	@Override
	public void start() {
		System.out.println("Disk.start()");	
	}

	@Override
	public void shutDown() {
		System.out.println("Disk.shutDown()");		
	}
}


class Memory implements Component{
	@Override
	public void start() {
		System.out.println("Memory.start()");		
	}

	@Override
	public void shutDown() {
		System.out.println("Memory.shutDown()");		
	}
}
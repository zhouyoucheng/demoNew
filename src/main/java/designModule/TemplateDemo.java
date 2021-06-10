package designModule;

/**
 * 模板方法模式
 * @author wb-zyc501131
 * 
 */
public class TemplateDemo {
	public static void main(String[] args) {
		DriveTemplate template = new Moto();
		template.drive();
		template = new Car();
		template.drive();
	}
}

abstract class DriveTemplate {
    
    public final void drive() {
        openDoor();
        startEngine();
        gear();
        go();
        brake();
        stop();
    }
    
    protected abstract void openDoor();
    
    protected void startEngine() {
        System.out.println("engine started !");
    }
    
    protected abstract void gear();
    
    protected void go() {
        System.out.println("running...");
    }
    
    protected abstract void brake();
    
    protected void stop() {
        System.out.println("stopped !");
    }
}

class Car extends DriveTemplate {

    @Override
    protected void openDoor() {
        System.out.println("keyless entry");
    }

    @Override
    protected void gear() {
        System.out.println("gear with hand");
    }

    @Override
    protected void brake() {
        System.out.println("brake with foot");
    }
}

class Moto extends DriveTemplate {

    @Override
    protected void openDoor() {
        System.out.println("no door actually");
    }

    @Override
    protected void gear() {
        System.out.println("gear with foot");
    }

    @Override
    protected void brake() {
        System.out.println("brake with hand");
    }
}

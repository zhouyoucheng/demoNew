package designModule.proxy;

/**
 * Created by supaur on 2021/4/9 10:38
 */
public final class  Man implements PeoPle{
    @Override
    public String getSex() {
        return "Man";
    }

    @Override
    public Integer getAge() {
        return 100;
    }

    @Override
    public final void eatWater() {
        System.out.println("i need water every day");
    }
}

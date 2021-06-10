package listener.spring;

/**
 * Created by supaur on 2020/11/30 13:37
 */
public class SpringEvent1 extends SpringEvent{
    @Override
    public String getClassName() {
        return this.getClass().getCanonicalName();
    }
}

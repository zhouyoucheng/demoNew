package listener.spring;

/**
 * Created by supaur on 2020/11/30 13:34
 */
public abstract class SpringListener<E extends SpringEvent> {
     public abstract void on(E event);

    public abstract Class<?> getListenClass();
}

package listener.spring;

/**
 * Created by supaur on 2020/11/30 13:36
 */
public class Listen1 extends SpringListener<SpringEvent1>{

    @Override
    public void on(SpringEvent1 event) {
        event.say();
    }

    @Override
    public Class<?> getListenClass() {
        return SpringEvent1.class;
    }
}

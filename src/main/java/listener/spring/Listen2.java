package listener.spring;

/**
 * Created by supaur on 2020/11/30 13:36
 */
public class Listen2 extends SpringListener<SpringEvent2>{

    @Override
    public void on(SpringEvent2 event) {
        event.say();
    }

    @Override
    public Class<?> getListenClass() {
        return SpringEvent2.class;
    }
}

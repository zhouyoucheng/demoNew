package listener.spring;

/**
 * Created by supaur on 2020/11/30 13:36
 */
public class Listen extends SpringListener<SpringEvent>{

    @Override
    public void on(SpringEvent event) {
        System.out.println("i am the father");
    }

    @Override
    public Class<?> getListenClass() {
        return SpringEvent.class;
    }
}

package listener.spring;

/**
 * Created by supaur on 2020/11/30 13:34
 * @author supaur
 */
public class SpringEvent {
    public void say() {
        System.out.println(getClassName());
    }

    public String getClassName(){return "ok";}
}

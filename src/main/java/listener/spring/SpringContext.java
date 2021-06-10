package listener.spring;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by supaur on 2020/11/30 13:35
 * @author supaur
 */
@Data
public class SpringContext {

    List<SpringListener> listeners = Lists.newArrayList(new Listen(), new Listen2(), new Listen1());
    public void publishEvent(SpringEvent springEvent){
        for (SpringListener listener : listeners) {
            ResolvableType generic = ResolvableType.forClass(listener.getClass()).as(SpringListener.class).getGeneric();
            Type type = generic.getType();
            Class<? extends Type> aClass1 = type.getClass();
            Class listenClass = listener.getListenClass();
            Class<? extends SpringEvent> aClass = springEvent.getClass();
            System.out.println(type + "----");
            System.out.println(aClass + "----");
            if (((Class)type).isAssignableFrom(aClass)) {
                listener.on(springEvent);
            }
        }
    }

    public static void main(String[] args) {
        SpringContext springContext = new SpringContext();
        springContext.publishEvent(new SpringEvent());
        springContext.publishEvent(new SpringEvent1());
        springContext.publishEvent(new SpringEvent2());


        PayloadApplicationEvent<SpringEvent1> applicationEvent = new PayloadApplicationEvent<>(springContext, new SpringEvent1());
        ResolvableType resolvableType = ((PayloadApplicationEvent<?>) applicationEvent).getResolvableType();
        System.out.println(resolvableType);
    }

}

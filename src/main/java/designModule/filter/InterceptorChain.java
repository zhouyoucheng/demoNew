package designModule.filter;

import com.google.common.collect.Lists;
import designModule.proxy.Man;
import designModule.proxy.PeoPle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by supaur on 2021/5/21 14:37
 * mybatis插件使用的责任链模式
 * 通过构建代理对象，每次代理的时候调用Interceptor集合中的一个
 *
 * @author supaur
 */
public class InterceptorChain {

    public static void main(String[] args) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Object interceptor(Object target) {
                System.out.println(111);
                return target;
            }
        };
        Interceptor interceptor1 = new Interceptor() {
            @Override
            public Object interceptor(Object target) {
                System.out.println(222);
                return target;
            }
        };
        List<Interceptor> interceptors = Lists.newArrayList(interceptor, interceptor1);
        Man man = new Man();
        PeoPle o = (PeoPle) buildChain(interceptors, man);
        o.eatWater();
    }

    public static Object buildChain(List<Interceptor> list, Object target) {
        Object wrap = target;
        for (Interceptor interceptor : list) {
            wrap = wrap(interceptor, wrap, true);
        }
        return wrap;
    }

    public static interface Interceptor {
        Object interceptor(Object target);
    }

    public static class Interceptor1 implements Interceptor {
        @Override
        public Object interceptor(Object target) {
            System.out.println("111");
            return target;
        }
    }

    /**
     * 注意动态代理，如果不调用目标方法，则是覆盖了目标原来的方法。
     *
     * @param interceptor
     * @param target
     * @param needWrap
     * @return
     */
    public static Object wrap(Interceptor interceptor, Object target, boolean needWrap) {
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        interceptor.interceptor(target);
                        return method.invoke(target);
                    }
                });
        return o;
    }

}

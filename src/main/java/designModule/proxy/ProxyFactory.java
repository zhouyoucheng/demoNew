package designModule.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by supaur on 2021/4/9 10:36
 */
public class ProxyFactory {

    public static void main(String[] args) {
        PeoPle instance1 = getInstance1(PeoPle.class);
//        instance1.eatWater();
//        instance1.getAge();
        System.out.println(instance1.getSex());

        System.out.println(111);
    }

    public static <T> T getInstance1(Class<T> source) {
        Object o = Proxy.newProxyInstance(source.getClassLoader(), new Class[]{source}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是代理对象before");
                System.out.println("我是代理对象after");
                Class<?> returnType = method.getReturnType();
                Constructor<?> constructor = returnType.getDeclaredConstructor();
                if (constructor != null) {
                    constructor.setAccessible(true);
                    Object o1 = constructor.newInstance();
                    return o1;
                }
                return null;
            }
        });
        return (T) o;
    }

    public static <T> T getInstance11(T source) {
        Object o = Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是代理对象before");
//                Object invoke = method.invoke(source, args);
                System.out.println("我是代理对象after");
                return new String("111");
            }

        });
        return (T) o;
    }

    public static <T> T getInstance(T source) {
        Object o = Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是代理对象before");
                Object invoke = method.invoke(source, args);
                System.out.println("我是代理对象after");
                return invoke;
            }

        });
        return (T) o;
    }

    public static <T> T getInstance3(T source) {
        Object o = Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(), new Handler());
        return (T) o;
    }

    static class Handler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(this, args);
        }
    }

}

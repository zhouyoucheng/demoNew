package designModule;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by supaur on 2021/4/8 12:28
 */
public class Factory<T>{


   public static  <T> T getInstance(Class<T> tClass, T sourceObject) {
       Object o = Proxy.newProxyInstance(tClass.getClassLoader(), tClass.getInterfaces(), new InvocationHandler() {
           @Override
           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               System.out.println("我是代理对象");
               Object invoke = method.invoke(sourceObject, args);
               System.out.println("我是代理对象执行结果");
               return invoke;
           }
       });
       return (T)o;
   }

}

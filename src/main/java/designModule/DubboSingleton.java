package designModule;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by supaur on 2021/5/28 14:28
 */
@Setter
@Getter
public class DubboSingleton {

    public static void main(String[] args) throws InterruptedException {
        Set<DubboSingleton> set = new HashSet<>();
        int size = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                DubboSingleton normalSingleton = getDubboSingleton("1");
                set.add(normalSingleton);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
        System.out.println(set.size());
        for (DubboSingleton dubboSingleton : set) {
            System.out.println("我是对象啊" + dubboSingleton);
        }

    }

    public DubboSingleton(String name) {
        this.name = name;
    }

    private String name;

    private static Map<String, Holder<DubboSingleton>> map = new ConcurrentHashMap<>();

    private static Map<String, DubboSingleton> normalMap = new ConcurrentHashMap<>();


    public static DubboSingleton getNormalSingleton(String name) {
        if (normalMap.get(name) == null) {
            DubboSingleton dubboSingleton = new DubboSingleton(name);
            normalMap.putIfAbsent(name, dubboSingleton);
        }
        return normalMap.get(name);

    }

    public static DubboSingleton getDubboSingleton(String name) {
        Holder<DubboSingleton> holder = map.get(name);
        if (holder == null) {
            holder = new Holder<>();
            map.putIfAbsent(name, holder);
            holder = map.get(name);
        }
        if (holder.getValue() == null) {
            synchronized (holder) {
                if (holder.getValue() == null) {
                    DubboSingleton dubboSingleton = new DubboSingleton(name);
                    holder.setValue(dubboSingleton);
                    map.put(name, holder);
                    return dubboSingleton;
                }
            }
        }

        return holder.getValue();

    }

    @Data
    private static class Holder<T> {
        private T value;
    }

}

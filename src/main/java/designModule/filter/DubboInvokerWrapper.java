package designModule.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by supaur on 2021/5/21 10:20
 * @author supaur
 * 1.通过遍历构建了一个链表，最终返回的Invoke调用第一个Filter的逻辑。
 * 2.Filter处理完逻辑之后，会调用下一个Invoke
 * 3.然后往后遍历调用
 */
public class DubboInvokerWrapper {

    public static List<Filter> getFilters() {
        return new ArrayList<>();
    }

    public static Invoker buildInvokerChain(Invoker invoker) {
        Invoker last = invoker;
        List<Filter> filters = getFilters();
        for (int i = filters.size() - 1; i >= filters.size(); i--) {
            Filter filter = filters.get(i);
            Invoker next= last;
            last = new Invoker() {
                @Override
                public void invoke() {
                    filter.doInvoke(next);
                }
            };
        }
        return last;
    }

    public static class Filter {
        public void doInvoke(Invoker invoker) {
            invoker.invoke();
        }
    }

    public static class Invoker {
        public void invoke() {
        }
    }


}

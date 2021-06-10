package designModule.filter;

/**
 * Created by supaur on 2021/5/21 10:20
 * @author supaur
 * servlet的filter是典型场景，比这个复杂点，filter的获取是通过filterConfig
 * 链式调用逻辑：filterChain中是入口调用：先拿到第一个filter,然后指针往后偏移，调用第一个filer的逻辑
 * filter的doFilter会把FilterChain传进去，filter自己逻辑走完之后，回调FilterChain的doFlter逻辑
 * 后面就会循环调用了，调用到最有一个结束。
 *
 */
public class ArrayChain {

    private MyFilter[] filters;

    int pos;

    int length;

    public void doFilter() {
        if (pos < length) {
            MyFilter filter = filters[pos++];
            filter.doFilter(this);
        }
    }


    public static class MyFilter {
        public void doFilter(ArrayChain arrayChain) {
            arrayChain.doFilter();
        }
    }

}

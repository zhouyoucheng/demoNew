package load;

import java.io.InputStream;
import org.junit.Test;

public class TestBean {

    private static int a = 10;

    public static void main(String[] args) throws Exception {
        // 一个简单的类加载器，逆向双亲委派机制
        // 可以加载与自己在同一路径下的Class文件
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name)
                    throws ClassNotFoundException {
                try {
                    String filename = name.substring(name.lastIndexOf(".") + 1)
                            + ".class";
                    InputStream is = getClass().getResourceAsStream(filename);
                    if (is == null) {
                        return super.loadClass(name);   // 递归调用父类加载器
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myClassLoader.loadClass("load.TestBean")
                .newInstance();
        TestBean testBean = new TestBean();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(testBean.getClass().getClassLoader());
        System.out.println(obj instanceof TestBean);
    }
}



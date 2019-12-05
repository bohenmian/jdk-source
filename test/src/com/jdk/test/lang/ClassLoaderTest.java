package src.com.jdk.test.lang;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {

        //获取TestClassLoader的类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        //打印出来TestClassLoader的类加载器
        System.out.println("ClassLoader is " + classLoader.toString());

        //打印TestClassLoader的父加载器
        System.out.println("ClassLoader is " + classLoader.getParent().toString());

        //获取int的类加载器,打印出结果为null
        System.out.println("ClassLoader is " + int.class.getClassLoader());

        //一个ClassLoad创建时没有指定父加载器,那么它默认的父加载器就是AppClassLoader
        //AppClassLoader的父加载器是ExtClassLoader,ExtClassLoader的父加载器是Bootstrap ClassLoader是由C++编写的,所以在Java中无法获得它的引用

        //自定义一个类的加载器
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {  //重写loadClass方法,让类的加载按照自己制定的规则来
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {   //如果该类的加载器为null,才使用父类的加载器
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };
        //此处我们得到结论针对于同一个类运用不同的构造器加载也会置于不同的命名空间
        //所以要唯一确定一个类需要类和类加载器共同确定
        Object obj = myClassLoader.loadClass("src.com.jdk.test.lang.ClassLoaderTest");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);

    }


}

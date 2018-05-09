package src.com.jdk.test.lang;

public class TestClassLoader {

    public static void main(String[] args) {

        //获取TestClassLoader的类加载器
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();

        //打印出来TestClassLoader的类加载器
        System.out.println("ClassLoader is " + classLoader.toString());

        //打印TestClassLoader的父加载器
        System.out.println("ClassLoader is " + classLoader.getParent().toString());

        //获取int的类加载器,打印出结果为null
        System.out.println("ClassLoader is " + int.class.getClassLoader());

        //一个ClassLoad创建时没有指定父加载器,那么它默认的父加载器就是AppClassLoader
        //AppClassLoader的父加载器是ExtClassLoader,ExtClassLoader的父加载器是Bootstrap ClassLoader是由C++编写的,所以在Java中无法获得它的引用
    }
}

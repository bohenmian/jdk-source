package src.com.jdk.test.lang;

public class ThreadLocalTest {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        //主线程
        threadLocalTest.set();

        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //其他的Thread再次set
                threadLocalTest.set();
                System.out.println(threadLocalTest.getLong());
                System.out.println(threadLocalTest.getString());
            }
        });
        thread.start();
        thread.join();

        //根据打印结果我们看到ThreadLocal确实是对线程进行绑定的,是因为ThreadLocal中的ThreadLocalMap是以线程为K,值的副本为V的数据结构
        //同时因为K的数据类型为WeakReference,需要注意内存泄露的情况发生
        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());

    }
}

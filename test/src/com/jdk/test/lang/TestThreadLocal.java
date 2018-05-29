package src.com.jdk.test.lang;

public class TestThreadLocal {

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
        TestThreadLocal testThreadLocal = new TestThreadLocal();

        //主线程
        testThreadLocal.set();

        System.out.println(testThreadLocal.getLong());
        System.out.println(testThreadLocal.getString());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //其他的Thread再次set
                testThreadLocal.set();
                System.out.println(testThreadLocal.getLong());
                System.out.println(testThreadLocal.getString());
            }
        });
        thread.start();
        thread.join();

        //根据打印结果我们看到ThreadLocal确实是对线程进行绑定的,是因为ThreadLocal中的ThreadLocalMap是以线程为K,值的副本为V的数据结构
        //同时因为K的数据类型为WeakReference,需要注意内存泄露的情况发生
        System.out.println(testThreadLocal.getLong());
        System.out.println(testThreadLocal.getString());

    }
}

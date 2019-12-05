package src.com.jdk.test.lang;

/**
 * Thread/Runnable模型
 * @see java.lang.Thread
 * 多线程模型:   JDK1.0
 */

public class RunnableTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                System.out.println("[Thread] Hello World..." + Thread.currentThread().getName());
            }
        }, "sub");
        thread.start();
        //主线程Main先执行
        System.out.println("[Thread] Starting..." + Thread.currentThread().getName());
    }
}

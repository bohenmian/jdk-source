package src.com.jdk.test.lang;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    //两个线程交替执行
    private static void demo() {
        Thread A = new Thread(() -> printNumber("A"));
        Thread B = new Thread(() -> printNumber("B"));
        A.start();
        B.start();
    }

    //让一个线程等待另一个线程执行完毕再执行
    private static void demo1() {
        Thread A = new Thread(() -> printNumber("A"));
        Thread B = new Thread(() -> {
            System.out.println("B开始等待A");
            try {
                A.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printNumber("B");
        });
        B.start();
        A.start();
    }

    //让两个线程按指定的顺序执行
    private static void demo2() {
        Object lock = new Object();
        Thread A = new Thread(() -> {
            synchronized (lock) {
                System.out.println("A 1");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A 2");
                System.out.println("A 3");
            }
        });

        Thread B = new Thread(() -> {
            synchronized (lock) {
                System.out.println("B 1");
                System.out.println("B 2");
                System.out.println("B 3");
                lock.notify();
            }
        });
        A.start();
        B.start();
    }

    private static void printNumber(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print:" + i);
        }
    }

    public static void main(String[] args) {
        demo();
        demo1();
        demo2();
    }
}

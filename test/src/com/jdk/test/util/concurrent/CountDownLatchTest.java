package src.com.jdk.test.util.concurrent;

import java.util.concurrent.CountDownLatch;

//适用于一个线程等待其他线程执行完毕才执行
public class CountDownLatchTest {

    public static void main(String[] args) {

        int work = 3;
        CountDownLatch countDownLatch = new CountDownLatch(work);
        new Thread(() -> {
            System.out.println("D is waiting for other three threads");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        for (char name = 'A'; name <= 'C'; name++) {
            final String runnerName = String.valueOf(name);
            new Thread(() -> {
                System.out.println(runnerName + " is working");
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(runnerName + " finished");
                countDownLatch.countDown();         //执行完一个线程就减1
            }).start();
        }
    }
}

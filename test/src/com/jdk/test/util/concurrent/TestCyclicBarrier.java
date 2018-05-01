package src.com.jdk.test.util.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//使用场景: 适用于当所有线程都到达时再一起执行下面程序的场景
public class TestCyclicBarrier {

    public static void main(String[] args) {
        int threadNum = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum);
        for (char name = 'A'; name <= 'C'; name++) {
            final String runnerName = String.valueOf(name);
            new Thread(() -> {
                long prepareTime = new Random().nextInt(10000) + 100;
                System.out.println(runnerName + " is preparing for time:" + prepareTime);
                try {
                    Thread.sleep(prepareTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(runnerName + " is prepared");
                    cyclicBarrier.await();      //等待所有线程到达才向下面执行
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(runnerName + " start running");
            }).start();
        }
    }
}

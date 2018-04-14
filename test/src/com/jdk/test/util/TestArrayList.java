package src.com.jdk.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestArrayList {

    private final static int THREADCOUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            //会存在线程安全问题,会发现如果程序执行成功后的结果并不等于预期值.或者在程序执行的过程中会出现ArrayIndexOutOfBoundsException异常
            test();
        }
    }

    public static void test() {
        List<Integer> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(THREADCOUNT);//让主线程等待THREADCOUNT执行完毕
        for (int i = 0; i < THREADCOUNT; i++) { //开了1000个线程
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        list.add(i);//每个线程向list中添加100个元素
                    }
                    countDownLatch.countDown();//线程执行完毕则减1
                }
            });
            thread.start();
        }
        try {
            countDownLatch.await();//等待所有子线程执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}

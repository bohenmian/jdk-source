package src.com.jdk.test.util.concurrent;

/**
 * Thread/Callable模型
 * @see java.util.concurrent.Callable
 * 多线程模型    JDK1.5
 */

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "[Thread : " + Thread.currentThread().getName() + "] Hello World";
            }
        });
        String value = future.get();
        System.out.println(value);
        executorService.shutdown();
    }
}

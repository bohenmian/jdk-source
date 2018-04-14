package src.com.jdk.test.util.concurrent;

/**
 * @see java.util.concurrent.Executor
 */

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> System.out.println("[Thread] Hello World..." + Thread.currentThread().getName()));
        if (executorService instanceof Executor) {
            executorService.shutdown();
        }
    }
}

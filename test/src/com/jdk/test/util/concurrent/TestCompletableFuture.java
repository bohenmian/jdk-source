package src.com.jdk.test.util.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 多线程模型JDK1.8
 * @see java.util.concurrent.CompletableFuture
 */

public class TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Hello World");
        //阻塞操作
        String value = completableFuture.get();
        System.out.println(value);


        //异步操作,阻塞操作(get)
        CompletableFuture asyncCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello World");
        });
        asyncCompletableFuture.get();
        System.out.println("Starting ...");

        CompletableFuture<String> supplyAsyncCompletableFuture = CompletableFuture.supplyAsync(() -> String.format("[Thread: %s] Hello World...\n", Thread.currentThread().getName()));
        String value1 = supplyAsyncCompletableFuture.get();
        System.out.println(value1);
    }


}

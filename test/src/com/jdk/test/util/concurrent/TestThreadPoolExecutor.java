package src.com.jdk.test.util.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(2, 3, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //添加两个任务,我们发现当任务数量小于核心线程的可用线程数的时候,会直接将任务加入当线程池中执行
        executor.execute(thread);
        executor.execute(thread);
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池线程数量" + executor.getPoolSize());
        System.out.println("阻塞队列任务数量" + executor.getQueue().size());

        //再添加两个任务,我们发现当来的任务数大于核心线程的可用线程数的时候,会将一部分任务放入线程池执行,但是多余的任务会被放进阻塞队列等待线程池的执行
        executor.execute(thread);
        executor.execute(thread);
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池线程数量" + executor.getPoolSize());
        System.out.println("阻塞队列任务数量" + executor.getQueue().size());

        //等待10s以后,我们发现核心线程数仍然是2,所以代表核心线程并不会被回收
        Thread.sleep(10000);
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池线程数量" + executor.getPoolSize());
        System.out.println("阻塞队列任务数量" + executor.getQueue().size());

        //此处我们发现如果核心线程数和最大线程数以及阻塞队列已经满了的话,那么执行任务的个数就根据我们设置的拒绝策略来决定
        //此处我们是直接丢弃该任务,所以最后看到的效果就只能看到执行了4个任务((corePoolSize)2 + (BlockQueue)1 + (maximumPoolSize)1 )
        for (int i = 0; i < 15; i++) {
            executor.execute(thread);
        }
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池线程数量" + executor.getPoolSize());
        System.out.println("阻塞队列任务数量" + executor.getQueue().size());
        System.out.println("最大线程数量" + executor.getMaximumPoolSize());
        executor.shutdown();
    }
}

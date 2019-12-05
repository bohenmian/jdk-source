package src.com.jdk.test.util.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.LongAdder;

/**
 * 多线程模型JDK1.7
 * Fork/Join的使用
 * @see java.util.concurrent.ForkJoinPool
 */

public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        LongAdder longAdder = new LongAdder();

        AddTask addTask = new AddTask(list, longAdder);

        forkJoinPool.invoke(addTask);

        forkJoinPool.shutdown();

        System.out.println(longAdder);
    }

    private static class AddTask extends RecursiveAction {

        private final List<Integer> list;

        private final LongAdder longAdder;

        public AddTask(List<Integer> list, LongAdder longAdder) {
            this.list = list;
            this.longAdder = longAdder;
        }

        @Override
        protected void compute() {
            int size = list.size();
            if (size > 1) {
                int partSize = size / 2;
                //分割任务
                List<Integer> leftPart = list.subList(0, partSize);
                AddTask leftTask = new AddTask(leftPart, longAdder);
                List<Integer> rightPart = list.subList(partSize, size);
                AddTask rightTask = new AddTask(rightPart, longAdder);
                invokeAll(leftTask, rightTask);
            } else {
                if (size == 0) {
                    return;
                }
                Integer value = list.get(0);
                longAdder.add(value.longValue());
            }
        }
    }
}

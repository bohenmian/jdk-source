package src.com.jdk.test.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Vector<Integer> vector = new Vector<>();
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        putArray(synchronizedList);
        putArray(vector);
        putArray(copyOnWriteArrayList);

    }

    private static void putArray(List<Integer> list) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final int key = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        list.add(key);
                    }
                }
            }).start();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}

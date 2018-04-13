package src.com.jdk.test.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, Integer> hashTable = new HashMap<>();
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        putMap(synchronizedMap);
        putMap(hashTable);
        putMap(concurrentHashMap);
    }

    private static void putMap(Map<Integer, Integer> map) {
        long begin = System.nanoTime();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    int result = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(result, result);
                        }
                    }).start();
                }
            }
        });
        long end = System.nanoTime();
        System.out.println(end - begin);
    }
}

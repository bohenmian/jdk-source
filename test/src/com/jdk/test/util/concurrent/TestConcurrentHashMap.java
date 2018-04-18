package src.com.jdk.test.util.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, Integer> hashTable = new Hashtable<>();
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        putMap(synchronizedMap);
        putMap(hashTable);
        putMap(concurrentHashMap);
    }

    private static void putMap(Map<Integer, Integer> map) {
        long begin = System.currentTimeMillis();
//        for (int k = 0; k < 100; k++) {      //循环100次,效果更明显
            for (int i = 0; i < 1000; i++) { //开1000个线程
                final int key = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < 100000; j++) {
                            map.put(key, key);        //每个线程向map添加1000个数
                        }
                    }
                }).start();
            }
//        }

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    //测试1.开1000个线程,每个线程插入1000个数,循环100次ConcurrentHashMap(6674) < HasTable(7257) < Collections.synchronizedMap(9743)
    //测试2.开1000个线程,每个线程插入10000个数,ConcurrentHashMap(507) < HasTable(699) < Collections.synchronizedMap(811)
    //测试3.开1000个线程,每个线程插入100000个数,ConcurrentHashMap(4838) > HasTable(1640) > Collections.synchronizedMap(1201)
    //结论1.开相同的线程数,如果插入1000~10000时ConcurrentHashMap的性能优于HasTable和Collections.synchronizedMap
    //结论2.开相同的线程数,如果插入数据太大(>100000),那么ConcurrentHashMap的性能小于HasTable和Collections.synchronizedMap(可能是因为hash碰撞)
}

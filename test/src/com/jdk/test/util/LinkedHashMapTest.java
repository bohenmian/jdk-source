package src.com.jdk.test.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest<K, V> extends LinkedHashMap<K, V> {

    private final int SIZE;

    public LinkedHashMapTest(int size) {
        super(); //调用无参构造方法,不启用LRU规则
        this.SIZE = size;
    }

    public LinkedHashMapTest(int size, float loadFactor, boolean accessOrder) {
        //调用含参构造方法,其中accessOrder必须设置为true才能实现LRU算法
        super(size, loadFactor, accessOrder);
        this.SIZE = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > SIZE; //如果存储到达最大值则删除一个,size()为HashMap中的方法,获得数组使用的容量
    }

    public static void main(String[] args) {
        //LinkedHashMap实现FIFO
        LinkedHashMapTest<Integer, Integer> map = new LinkedHashMapTest<>(10);
        for (int i = 0; i++ < 10; ) {
            map.put(i, i);
        }
        System.out.println("起始存储情况:" + map.toString());  //打印起始存储情况

        map.put(8, 8);
        System.out.println("命中一个已存在的数据" + map.toString());//打印命中之后的情况

        map.put(11, 11);
        System.out.println("新增一个数据" + map.toString());

        //LinkedHashMap实现LRU算法

        LinkedHashMapTest<Integer, Integer> linkedHashMap = new LinkedHashMapTest<>(10, 0.75f, true);
        for (int i = 0; i++ < 10; ) {
            linkedHashMap.put(i, i);
        }
        System.out.println("起始存储情况："+linkedHashMap.toString());  //打印起始存储情况


        linkedHashMap.get(7);
        System.out.println("命中一个已存在的数据" + linkedHashMap.toString());

        linkedHashMap.put(8, 8 + 1);
        System.out.println("覆盖一个已存在的数据：" + linkedHashMap.toString());

        linkedHashMap.put(11, 11);
        System.out.println("增加一个数据" + linkedHashMap.toString());

    }
}

package src.com.jdk.test.lang;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class TestObject {

    private int x;
    private int y;

    public TestObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    只覆盖Object的equals方法,不覆盖hashcode()方法,会导致数据的不唯一性
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String[] args) {
        Collection set = new HashSet();
        TestObject test1 = new TestObject(1, 1);
        TestObject test2 = new TestObject(1, 1);
        //此处test1.equals()返回true,所以test1和test2应该是同一个对象
        System.out.println(test1.equals(test2));
        //HashSet为空,直接将test1加入set
        set.add(test1);

        //test2和test1为同一个对象,因为没有覆盖Object的hashCode()方法.
        // 所以JDK默认使用Object的hashCode方法,返回内存地址转换后的整数,
        // 因为test1和test2在Java Heap上分配的内存不同,所以hashCode返回值不同,所以判定为HashSet中不存在这个对象,导致test2存入set,造成一个set中有两个相同的对象
        set.add(test2);
        set.add(test1);

        Iterator iterator = set.iterator();
        //此处打印出来HashSet中有两个相同的对象
        while (iterator.hasNext()) {
            Object object = iterator.next();
            System.out.println(object);
        }
    }
    //同理,如果覆盖了hashCode()方法而不覆盖equals()方法,仍然会造成数据的不唯一性,因为两个相同对象hashCode()后返回的int值相同
    // 但Object的equals()比较的是两个对象的地址是否相同而不是值相同,所以仍然会导致两个对象的存储不唯一性

    //综上:想要保证元素的唯一性,必须要同时覆盖hashCode()方法和equals()方法
}

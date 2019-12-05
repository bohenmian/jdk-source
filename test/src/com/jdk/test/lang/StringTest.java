package src.com.jdk.test.lang;

public class StringTest {

    public static void main(String[] args) {
        //此处s1是直接使用的赋值操作,当编译器编译的时候,先检查class文件中是否包含该字符串常量,如果包含,则直接返回常量池中对象的引用,没有则在常量池新建对象
        String s1 = "abc";
        //将常量池中abc的引用指向abc
        String s2 = "abc";

        //字符串new的时候先在Java Heap上分配,然后对象的内容为常量池里面对象字符串的一个引用,每次new分配的内存都不同
        String str1 = new String("abc");
        String str2 = new String("abc");

        //常量abc是常量池abc的一个引用
        System.out.println(s1 == "abc");  //true

        //s1,s2都是常量池abc的一个引用
        System.out.println(s1 == s2);  //true

        //s1是作为常量放在class文件中的常量区,而str1则是在Java Heap上分配,两者没在一个区域
        System.out.println(s1 == str1);  //false

        //str1,str2都是在Java Heap上分配,但str1,str2分配的内存不相同
        System.out.println(str1 == str2);  //false

        //String重写了Object的equals方法,Object的equals方法比较两个变量的地址是否相等,而String中的equals比较两个字符串是否相等
        System.out.println(s2.equals(str1));  //true

        //String的intern方法返回对象在方法区常量池的一个引用,如果没有则增加一个等于str1的字符串并返回它的引用
        System.out.println(str1.intern() == s1);  //true
        System.out.println(str1.intern() == str2.intern());  //true

        //编译期无法确定,JVM对String a = "a"对象放在常量池是在编译时做的,而String ab = a + b则是在运行时才能知道
        String a = "a";
        String b = "aaa";
        String ab = a + b;
        System.out.println(ab == "aaaa");  //false

        //编译期优化,编译期JVM将常量字符串优化为连接后的值
        String aa = "a" + "aa";
        System.out.println(aa == b); //true

        //编译时无法确定b1,返回false
        String b1 = "aa";
        System.out.println(a + b1 == b); // false

        //String被声明为final,在编译时解析为一个常量
        final String b2 = "aa";
        String ss = "a" + b2;
        System.out.println(ss == b);  //true

    }


}

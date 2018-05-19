package src.com.jdk.test.lang;

/**
 * 测试类中的初始化顺序
 * 从输出结果可以看出类中初始化顺序为
 * 父类静态代码块和父类静态成员 -> 子类静态代码块和子类静态成员 -> 父类非静态代码块和普通成员 -> 父类构造方法 -> 子类非静态代码块和普通成员 -> 子类构造方法
 * 原理是因为在JVM中静态代码块和静态成员的初始化是在准备阶段而非静态非静态代码块初始化是在初始化阶段
 */

public class ParentInitialOrder {

    private static String staticField = "Parent Static Field Initial";

    private String field = "Parent Field Initial";

    //父类静态代码块
    static {
        System.out.println(staticField);
        System.out.println("Parent Static Patch Initial");
    }

    //父类非静态代码块
    {
        System.out.println(field);
        System.out.println("Parent Field Patch Initial");
    }

    //父类构造方法
    public ParentInitialOrder() {
        System.out.println("Parent Structure Initial");
    }

    public static void main(String[] args) {
        new ChildInitialOrder();
    }


}

class ChildInitialOrder extends ParentInitialOrder {

    private static String staticField = "Child Static Field Initial";

    private String field = "Child Field Initial";

    //子类静态代码块
    static {
        System.out.println(staticField);
        System.out.println("Child Static Patch Initial");
    }

    //子类非静态代码块
    {
        System.out.println(field);
        System.out.println("Child Field Patch Initial");
    }

    //子类构造方法
    public ChildInitialOrder() {
        System.out.println("Child Structure Initial");
    }
}

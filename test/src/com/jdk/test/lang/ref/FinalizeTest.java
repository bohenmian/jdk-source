package src.com.jdk.test.lang.ref;

/**
 *1.通过可达性分析判断该对象是否和其他对象存在引用链,如果不存在引用链,那么判断是否需要执行第一次标记,筛选的条件是是否有必要执行finalize方法
 *如果重写了finalize方法并且该对象没有执行过finalize方法,那么代表它是有必要的
 *2.如果是有必要执行finalize方法,那么就把该对象加入一个F-Queue队列中,在对列中如果有对象引用,那么就可以不判定为死亡,如果没有引用,那么就判定为真正的死亡
 */
public class FinalizeTest {

    public static FinalizeTest finalizeTest;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("嗯，已经执行了finalize()方法了");
        FinalizeTest.finalizeTest = this;   //用this来指向testFinalize,避免testFinalize的引用为空
    }

    private static void save() throws InterruptedException {

        finalizeTest = null; //将testFinalize的引用置为null,代表没有对象引用,help GC
        System.gc();  //通知系统通知需要GC


        Thread.sleep(500);//加入F-Queue队列中的Finalizer线程优先级比较低

        if (finalizeTest != null) {
            System.out.println("我还活着~你咬我啊~");

        } else {
            System.out.println("啊哦，我挂了~");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        finalizeTest = new FinalizeTest();
        //执行save方法后发现执行了System.gc()但是对象并没有回收,是因为在重写的finalize方法中通过this将自己的引用赋给自己
        //同时也发现执行了finalize方法只执行了一次
        save();
        //执行save方法后发现执行了System.gc()但是对象已经被回收了,是因为对象已经执行过一次finalize方法了,这次调用save方法并没有执行finalize方法
        save();
    }
}

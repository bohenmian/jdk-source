package src.com.jdk.test.util;

import java.util.HashMap;

public class TestHashMap {

    private static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100000; i++) {
                    int result = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(result, result);
                        }
                    }, "test" + i).start();
                }
            }
        });
        thread.start();

        Thread.sleep(5000);
        for (int i = 1; i <= 100000; i++) {
            Integer value = map.get(i);
            if (value == null) {
                System.out.println(i + "数据丢失");
            }
        }
        System.out.println("end ....");
    }
}

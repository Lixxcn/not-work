package daily;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/28 9:38
 */
public class MThread {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池:
        long startTime = System.currentTimeMillis();    //获取开始时间

        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            System.out.println("task" + i + "进入处理");
            es.submit(new Task("" + i));
            System.out.println("task" + i + "提交了");
        }
        // 关闭线程池:
        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while (!es.isTerminated()){
//            try
//            {
//                Thread.sleep(1000);
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//
//        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}

class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("start task " + name);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("end task " + name);
    }
}

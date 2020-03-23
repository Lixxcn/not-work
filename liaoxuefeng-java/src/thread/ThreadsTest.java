package thread;

import java.lang.annotation.Target;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ThreadsTest {

    public static void main(String[] args) throws InterruptedException {

        int i = 7;
        Scanner scanner = new Scanner(System.in);
        System.out.println("执行哪个功能：");
        i = scanner.nextInt();
        while (i != -1){
            switch (i) {
                case 0:
                    //线程创建
                    test0();
                    break;
                case 1:
                    //主线程与线程先后没有关系
                    test1();
                    break;
                case 2:
                    //join
                    test2();
                    break;
                case 3:
                    //中断线程1
                    test3();
                    break;
                case 4:
                    //中断线程2
                    test4();
                    break;
                case 5:
                    //中断线程3
                    test5();
                    break;
                case 6:
                    //守护线程
                    test6();
                    break;
                case 7:
                    //线程同步
                    test7();
                    break;
                case 8:
                    //线程同步
                    test8();
                    break;
                case 9:
                    //使用ThreadLocal
                    test9();
                    break;
                default:
                    System.out.println("执行完毕");
            }
            Thread.sleep(2000);
            System.out.println("执行哪个功能：");
            i = scanner.nextInt();
        }
        System.out.println("---end---");

    }
    static void test9(){
        log("start main...");
        new Thread(() -> {
            log("run task...");
        }).start();
        new Thread(() -> {
            log("print...");
        }).start();
        log("end main.");
    }
    static void log(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
    static void test8() throws InterruptedException {
        var q = new TaskQueue();
        var ts = new ArrayList<Thread>();
        for (int i=0; i<5; i++) {
            var t = new Thread() {
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String s = q.getTask();
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }
        var add = new Thread(() -> {
            for (int i=0; i<10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try { Thread.sleep(100); } catch(InterruptedException e) {}
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (var t : ts) {
            t.interrupt();
        }
    }
    static void test7() throws InterruptedException {
        var add = new AddThread();
        var dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
    static void test6(){
        TimerThread t = new TimerThread();
        t.setDaemon(true);
        t.start();
    }
    static void test5() throws InterruptedException {
        HelloThread1 t = new HelloThread1();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }
    static void test4() throws InterruptedException {
        Thread t = new MyThread4();
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束
        System.out.println("end");
    }
    static void test3() throws InterruptedException {
        Thread t = new MyThread3();
        t.start();
        Thread.sleep(5); // 暂停1毫秒
        t.interrupt(); // 中断t线程
//        t.join(); // 等待t线程结束
        System.out.println("end");
    }
    static void test2() throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello end");
        });
        System.out.println("start");
        t.start();
        t.join(200);//超过200ms不再等待
        System.out.println("end");
    }
    public static void test0(){
        //第一种
        Thread t0 = new MyThread();
        t0.start();
        //第二种
        Thread t1 = new Thread(new MyRuuable());
        t1.start();
        //第三种
        Thread t2 = new Thread(() -> {
            System.out.println("start new 2 thread!");
        });
        t2.start();
    }
    public static void test1(){
        System.out.println("test1 start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
                System.out.println("thread end.");
            }
        };
        t.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}
        System.out.println("test1 end...");
    }
}

class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("start new 0 thread!");
    }
}

class MyRuuable implements Runnable{
    @Override
    public void run() {
        System.out.println("start new 1 thread!");
    }
}

class MyThread3 extends Thread {
    public void run() {
        int n = 0;
        while (! isInterrupted()) {
            n ++;
            System.out.println(n + " hello!");
        }
    }
}

class MyThread4 extends Thread {
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        hello.interrupt();
    }
}

class HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
class HelloThread1 extends Thread {
    public volatile boolean running = true;
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
class TimerThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

//没有同步的情况
//class Counter {
//    public static int count = 0;
//}
//
//class AddThread extends Thread {
//    public void run() {
////        for (int i=0; i<10000; i++) { Counter.count += 1; }
//        for (int i=0; i<10000; i++) { Counter.count ++; }
//    }
//}
//
//class DecThread extends Thread {
//    public void run() {
////        for (int i=0; i<10000; i++) { Counter.count -= 1; }
//        for (int i=0; i<10000; i++) { Counter.count --; }
//    }
//}

//同步了的情况
class Counter {
    public static final Object lock = new Object();
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lock) {
                Counter.count += 1;
            }
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lock) {
                Counter.count -= 1;
            }
        }
    }
}
class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}


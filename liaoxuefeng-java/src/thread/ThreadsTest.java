package thread;

import java.lang.annotation.Target;

public class ThreadsTest {

    public static void main(String[] args) throws InterruptedException {
        int i = 4;
        switch (i){
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
             default:
                 System.out.println("执行完毕");

        }
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
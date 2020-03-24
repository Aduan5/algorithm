package com.aduan.study.thread;

/**
 * 线程A只能输出A
 * 线程B只能输出B
 * 主线程不能输出A或B
 * 请尽可能 不使用封装好的工具方法或类 来调度线程，然后输出如下结果：
 * ABBBB ABBBB ABBBB A … …
 * 加分项：增加线程B1，行为和B一样，要求B和B1负载均衡（TODO ）
 */
public class ABPrintTest {
    // 总共打印的次数
    public int n;
    public volatile boolean printA = true;

    public ABPrintTest(int n) {
        this.n = n;
    }

    public synchronized void printA() {
        while (!printA) {
            waitThread();
        }
        System.out.print("线程：" + Thread.currentThread().getName());
        System.out.println(" ----> A");
        printA = false;
        //唤醒所有在线程池中冻结的线程，会把所有都唤醒
        this.notifyAll();
    }

    public synchronized void printB() {
        while (printA) {
            waitThread();
        }
        System.out.print("线程：" + Thread.currentThread().getName());
        System.out.println(" ---> BBBB ");
        printA = true;
        this.notifyAll();
    }

    private void waitThread() {
        try {
//            Thread.sleep(500);
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ABPrintTest abPrintTest = new ABPrintTest(3);

        new Thread(() -> {
            while (true) {
                abPrintTest.printA();
            }
        }, "Thread-A").start();
        new Thread(() -> {
            while (true) {
                abPrintTest.printB();
            }
        }, "Thread-B").start();
        new Thread(() -> {
            while (true) {
                abPrintTest.printB();
            }
        }, "Thread-B1").start();
    }
}

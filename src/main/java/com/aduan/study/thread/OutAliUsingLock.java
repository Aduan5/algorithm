package com.aduan.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 题目（多线程）：
 * 有3个线程和一个公共的字符数组，线程1的功能就是向数组中输出A，线程2的功能就是向数组中输出B，线程3的功能就是向数组中输出C，
 * 要求按顺序向数组赋值ABCABCABC，Ali的个数有线程函数1的参数指定。
 */
public class OutAliUsingLock {

    private static int state;

    // 定义字符数组
    private static char[] charArray = {};
    // 定义数组下标
    private static int index = 0;

    // 同步计数器
    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    // 定义一个锁
    private static ReentrantLock lock = new ReentrantLock();

    public static class A implements Runnable {
        public A(int num) {
            charArray = new char[num * 3];
        }

        @Override
        public void run() {
            output('A', 0);
            countDownLatch.countDown();
        }
    }

    public static class B implements Runnable {
        @Override
        public void run() {
            output('l', 1);
            countDownLatch.countDown();
        }
    }

    public static class C implements Runnable {
        @Override
        public void run() {
            output('i', 2);
            countDownLatch.countDown();
        }
    }

    private static void output(char s, int targetState) {
        for (int i = 0, len = charArray.length / 3; i < len; ) {
            lock.lock();
            if (state % 3 == targetState) {
                System.out.println(index + " -- " + s);
                charArray[index++] = s;
                state++;
                i++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new A(3));
        executor.execute(new B());
        executor.execute(new C());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println(String.valueOf(charArray));
        executor.shutdown();
    }
}

package com.aduan.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock控制交替打印A、B、C
 */
public class AlternatePrintABC {
    private static Lock lock = new ReentrantLock();
    // 创建三个对象  要为特定 Lock 实例获得 Condition 实例，请使用其 newCondition() 方法。
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();

    private static String str = "A";

    public static void print(Condition await, Condition signal, String printStr, String nextStr) {
        lock.lock();
        try {
            if (!printStr.equals(str)) {
                try {
                    await.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(str);
            // 唤醒A
            signal.signal();
            str = nextStr;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int n = 3;
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                print(conditionA, conditionB, "A", "B");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                print(conditionB, conditionC, "B", "C");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                print(conditionC, conditionA, "C", "A");
            }
        }).start();
    }
}

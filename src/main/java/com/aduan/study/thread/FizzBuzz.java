package com.aduan.study.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 编写一个程序，输出从1到n的数字的字符串表示。
 * 但对于三的倍数，它应输出“Fizz”而不是数字，
 * 对于五的倍数，应该输出“Buzz”。
 * 对于三和五共同的倍数，应输出“FizzBuzz”。例如：
 * <p>
 * 输入：n = 15
 * 输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 * <p>
 * 要求多线程版本实现，分别四个线程
 */
public class FizzBuzz {
    private int n;
    // 控制 fizz 的输出
    Semaphore sa = new Semaphore(0);
    // 控制 buzz 的输出
    Semaphore sb = new Semaphore(0);
    // 控制 fuzzbuzz 的输出
    Semaphore sc = new Semaphore(0);
    // 控制 number 的输出
    Semaphore sd = new Semaphore(0);
    private int cur = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (cur <= n) {
            sa.acquire();
            printFizz.run();
            sd.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (cur <= n) {
            sb.acquire();
            printBuzz.run();
            sd.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (cur <= n) {
            sc.acquire();
            printFizzBuzz.run();
            sd.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            if (cur % 3 != 0 && cur % 5 != 0) {
                printNumber.accept(cur);
            } else if (cur % 3 == 0 && cur % 5 != 0) {
                sa.release();
                sd.acquire();
            } else if (cur % 5 == 0 && cur % 3 != 0) {
                sb.release();
                sd.acquire();
            } else {
                sc.release();
                sd.acquire();
            }
            cur++;
            if (cur > n) {
                sa.release();
                sb.release();
                sc.release();
                break;
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(16);
        Runnable rFizz = () -> System.out.print(" fizz ");
        Runnable rBuzz = () -> System.out.print(" buzz ");
        Runnable rFizzBuzz = () -> System.out.print(" fizzbuzz ");
        IntConsumer pNumber = x -> System.out.print(" " + x + " ");

        new Thread(() -> {
            try {
                fizzBuzz.fizz(rFizz);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(rBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(rFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(pNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}

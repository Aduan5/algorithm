package com.aduan.study.classload;

/**
 * @Description TODO
 * @Author DuanJun
 * @Date 2019/12/10 10:03
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2(); // ②
    // ①
    private static int a = 0;

    private static int b;


    private Singleton2() {
        a++;
        b++;
    }

    public static Singleton2 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        System.out.println("a = " + instance.a);
        System.out.println("b = " + instance.b);
    }
}

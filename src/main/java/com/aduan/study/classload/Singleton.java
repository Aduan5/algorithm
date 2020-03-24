package com.aduan.study.classload;

/**
 * @Description TODO
 * @Author DuanJun
 * @Date 2019/12/10 10:03
 */
public class Singleton {
    // ①
    private static int a = 0;

    private static int b;

    private static Singleton instance = new Singleton(); // ②

    private Singleton() {
        a++;
        b++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("a = " + instance.a);
        System.out.println("b = " + instance.b);
    }
}

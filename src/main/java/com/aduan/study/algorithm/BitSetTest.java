package com.aduan.study.algorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * 有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来？
 */
public class BitSetTest {

    public static void main(String[] args) {
//        bitSetTest();
        char c = 'a';
        System.out.println((int)c);
    }

    public static void bitSetTest() {
        int n = 10000000; //随机数的个数
        int m = 100000000; //最大的数字
        // 产生随机数
        Random random = new Random();
        // 随机生成一千万个数
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(m));
        }
        System.out.println("产生的随机数有");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        //根据位图法存入BitSet中
        BitSet bitSet = new BitSet(m);
        for (int i = 0; i < n; i++) {
            bitSet.set(list.get(i));
        }

        System.out.println("\n0~1亿不在上述随机数中有" + bitSet.size());
        // 循环判断不在位图中的数
        for (int i = 0; i < m; i++) {
            if (!bitSet.get(i)) {
                System.out.print(i + " ");
            }
        }
    }
}

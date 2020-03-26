package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 排序算法之 -- 插入排序 - 直接插入排序
 *
 * @author dj
 * @date 2020-03-26
 */
public class InsertionSort {

    public static void main(String[] args) {
//        int[] nums = {6, 5, 4, 3, 2, 1};
//        int[] nums = {1, 2, 3, 4, 5, 6};
        int[] nums = {4, 6, 3, 1, 5, 2};
        sort(nums);
    }

    public static void sort(int[] nums) {
        System.out.print("插入排序开始 ----------");
        printArray(nums);
        System.out.println("");

        int temp, j, totalChange = 0;
        // 默认第 0 个元素是有序的，因此从第 1 个元素开始比较
        for (int i = 1; i < nums.length; i++) {
            System.out.println("第" + i + "躺排序 -- ");
            // 把要插入的元素记录下来 -- 减少交换的次数
            temp = nums[i];
            System.out.println("    待比较的数值：" + temp);
            // 从 i 前一个元素开始倒叙比较
            j = i - 1;
            // 找出最小的一个
            while (j >= 0 && temp < nums[j]) {
                System.out.print("       位置 " + (j + 1) + " 替换为位置 " + j + " 替换后数组为：");
                // 把数组前一个 赋值 到后一个位置
                nums[j + 1] = nums[j];
                j--;
                printArray(nums);
                totalChange++;
            }
            if (nums[j + 1] != temp) {
                System.out.println("    位置 " + (j + 1) + " 替换为 " + temp);
                nums[j + 1] = temp;
                totalChange++;
            }
            System.out.print("    第" + i + "躺排序后的结果：");
            printArray(nums);
        }
        System.out.println("插入排序完成 -------------------总共交换：" + totalChange);
    }


    public static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
}

/**
 * 程序运行结果：
 * <pre>
 * 插入排序开始 ----------[4, 6, 3, 1, 5, 2]
 *
 * 第1躺排序 --
 *     待比较的数值：6
 *     第1躺排序后的结果：[4, 6, 3, 1, 5, 2]
 * 第2躺排序 --
 *     待比较的数值：3
 *        位置 2 替换为位置 1 替换后数组为：[4, 6, 6, 1, 5, 2]
 *        位置 1 替换为位置 0 替换后数组为：[4, 4, 6, 1, 5, 2]
 *     位置 0 替换为 3
 *     第2躺排序后的结果：[3, 4, 6, 1, 5, 2]
 * 第3躺排序 --
 *     待比较的数值：1
 *        位置 3 替换为位置 2 替换后数组为：[3, 4, 6, 6, 5, 2]
 *        位置 2 替换为位置 1 替换后数组为：[3, 4, 4, 6, 5, 2]
 *        位置 1 替换为位置 0 替换后数组为：[3, 3, 4, 6, 5, 2]
 *     位置 0 替换为 1
 *     第3躺排序后的结果：[1, 3, 4, 6, 5, 2]
 * 第4躺排序 --
 *     待比较的数值：5
 *        位置 4 替换为位置 3 替换后数组为：[1, 3, 4, 6, 6, 2]
 *     位置 3 替换为 5
 *     第4躺排序后的结果：[1, 3, 4, 5, 6, 2]
 * 第5躺排序 --
 *     待比较的数值：2
 *        位置 5 替换为位置 4 替换后数组为：[1, 3, 4, 5, 6, 6]
 *        位置 4 替换为位置 3 替换后数组为：[1, 3, 4, 5, 5, 6]
 *        位置 3 替换为位置 2 替换后数组为：[1, 3, 4, 4, 5, 6]
 *        位置 2 替换为位置 1 替换后数组为：[1, 3, 3, 4, 5, 6]
 *     位置 1 替换为 2
 *     第5躺排序后的结果：[1, 2, 3, 4, 5, 6]
 * 插入排序完成 -------------------总共交换：14
 * </pre>
 */
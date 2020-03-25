package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 排序算法之 -- 插入排序
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
        for (int i = 1; i < nums.length; i++) {
            System.out.println("第" + i + "躺排序 -- ");
            temp = nums[i];
            System.out.println("    待比较的数值：" + temp);
            j = i - 1;
            // 找出最小的一个
            while (j >= 0 && temp < nums[j]) {
                System.out.print("       位置 " + (j + 1) + " 替换为位置 " + j + " 替换后数组为：");
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

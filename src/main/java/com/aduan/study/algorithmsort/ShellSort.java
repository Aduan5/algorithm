package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * Shell排序
 */
public class ShellSort {

    public static void sort(int[] nums) {
        System.out.print("Shell排序开始 ----------");
        printArray(nums);

        int temp, j, x = 0, totalChange = 0;
        // 划组排序
        for (int r = nums.length / 2; r >= 1; r /= 2) {
            System.out.println("第" + (x + 1) + "躺排序---");
            for (int i = r; i < nums.length; i++) {
                temp = nums[i];
                System.out.println("    待比较的数值：" + temp);
                j = i - r;
                while (j >= 0 && temp < nums[j]) {
                    System.out.print("       位置 " + (j + r) + " 替换为位置 " + j + " 替换后数组为：");
                    nums[j + r] = nums[j];
                    j -= r;
                    printArray(nums);
                    totalChange++;
                }
                if (nums[j + r] != temp) {
                    System.out.print("    位置 " + (j + r) + " 替换为 " + temp + " 替换后数组为：");
                    nums[j + r] = temp;
                    printArray(nums);
                    totalChange++;
                }
            }
            x++;
            System.out.print("第" + x + "躺排序后的结果：");
            printArray(nums);
        }
        System.out.println("Shell排序序完成 -------------------总共交换：" + totalChange);
    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 3, 2, 1};
        sort(nums);
    }

    public static void printArray(int[] nums) {
        Arrays.stream(nums).forEach(num -> System.out.print(num));
        System.out.println("");
    }
}

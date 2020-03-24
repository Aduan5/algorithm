package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void sort(int[] nums) {
        System.out.println("选择排序开始 -------------------");
        int index, temp;
        boolean swapped = true;
        for (int i = 0, len = nums.length; i < len - 1 && swapped; i++) {
            swapped = false;
            System.out.println("第" + (i + 1) + "躺排序 -- ");
            index = i;
            // 选出最小数
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }

            if (i != index) {
                System.out.println("    " + nums[i] + "和" + nums[index] + "交换");
                temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                swapped = true;
            }

            System.out.print("    第" + (i + 1) + "躺排序后的结果：");
            Arrays.stream(nums).forEach(num -> System.out.print(num));
            System.out.println("");
        }
        System.out.println("选择排序完成 -------------------");
    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 7, 3, 9, 1};
        sort(nums);
    }
}

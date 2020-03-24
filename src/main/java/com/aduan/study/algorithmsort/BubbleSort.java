package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 冒泡排序算法
 */
public class BubbleSort {

    public static void sort(int[] nums) {
        System.out.println("冒泡排序开始·····················");

        // 定义标志 - 是否数组有序 - 当比较一趟下来有序后就无需再进行下一趟了
        boolean sortedFlag = true;
        int i = 1;
        for (int len = nums.length; i < len && sortedFlag; i++) {
            System.out.println("第" + i + "躺排序 -- ");
            sortedFlag = false;
            for (int j = 0; j < len - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    System.out.println("    " + nums[j] + "和" + nums[j + 1] + "交换");
                    int tem = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tem;
                    sortedFlag = true;
                }
            }
            System.out.print("    第" + i + "躺排序后的结果：");
            Arrays.stream(nums).forEach(num -> System.out.print(num));
            System.out.println("");
        }
        System.out.println("总共排序" + (i - 1) + "躺");
        System.out.println("冒泡排序结束·····················");
    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 3, 2, 1};
        sort(nums);
    }
}

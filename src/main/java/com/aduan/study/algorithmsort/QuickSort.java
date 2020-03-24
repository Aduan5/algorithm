package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 快速排序算法：分治法
 * 在每一轮挑选一个基准元素，并让其他比它大的元素移动到数列一边，比它小的元素移动到数列的另一边，从而把数列拆解成了两个部分
 */
public class QuickSort {
    public static int total = 0;

    public static void sort(int[] nums, int left, int right) {
        // 递归结束条件：left大等于right的时候
        if (left >= right) {
            return;
        }
        System.out.println("排序数组：" + Arrays.toString(nums) + " 待排序下标：" + left + " - " + right);
        // 得到基准元素位置
        int pivotIndex = partition(nums, left, right);
        System.out.println("     找到基准元素下标：" + pivotIndex + " 元素值：" + nums[pivotIndex]);
        // 用分治法递归数列的两部分
        sort(nums, left, pivotIndex - 1);
        sort(nums, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            //控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //控制right指针比较并右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //交换left和right指向的元素
            if (left < right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
                total++;
            }
        }
        //pivot和指针重合点交换
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;
        total++;
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 3, 2, 1};
        sort(nums, 0, nums.length - 1);
        System.out.println("总交换次数：" + total);
        System.out.println(Arrays.toString(nums));
    }

}

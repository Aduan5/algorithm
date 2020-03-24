package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 排序算法之 -- 冒泡排序算法
 * <p>
 * 核心思想：类似水泡冒泡的思想，每次都是大的水泡从瓶底往上冒泡。
 * <p>
 * 排序过程：每次选择数组第一个元素，逐个和后面的元素比较和交换，每一趟排序后数组尾部的元素便是有序的，因此冒泡排序算法最坏情况下需要进行 n-1 趟排序
 * <p>
 * 时间复杂度：最坏情况下 O(n^2)；最优情况：O(n)；平均时间复杂度：O(n^2)
 *
 * @author duanjun
 * @desc sum up the bubble sort from my old code of university period
 * @date 2020.03.24
 */
public class BubbleSort {

    public static void sort(int[] nums) {
        System.out.print("冒泡排序开始··········");
        printArray(nums);

        // 定义标志 - 是否数组有序 - 当比较一趟下来有序后就无需再进行下一趟了
        // 是为了解决数组基本有序的情况下，无效for循环比较的问题
        boolean sortedFlag = true;
        int i = 1;
        // 首先定义最外层需要排序的趟数 == n-1 趟
        for (int len = nums.length; i < len && sortedFlag; i++) {
            System.out.println("第 " + i + " 躺排序 ---- ");
            // 每一趟排序开始时，初始化是否有序标志为 false
            sortedFlag = false;
            // j=0 -- 每次循环都是从第一个元素开始组个往后面比较交换
            // 内层比较交换的循环：j < len -i 解释：由于每排序 i 趟后，数组后面的 i 个元素是有序的，因此只需比较 前面部分元素即可
            for (int j = 0; j < len - i; j++) {
                // 比较 -- 这儿控制是从小到大还是从大到小排序
                if (nums[j] > nums[j + 1]) {
                    System.out.println("    " + nums[j] + " 和 " + nums[j + 1] + " 交换");
                    int tem = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tem;
                    // 设置是否有序标志 -- 只要每一趟比较中有交换，则代表该趟排序后数组可能还不是全有序的。
                    sortedFlag = true;
                }
            }
            System.out.print("  第 " + i + " 躺排序后的结果：");
            printArray(nums);
        }
        System.out.println("总共排序 " + (i - 1) + " 躺");
        System.out.println("冒泡排序结束·····················");
    }

    public static void main(String[] args) {
        // 最坏情况排序数组
        int[] nums = {6, 5, 4, 3, 2, 1};
        // 完全有序情况排序数组
//        int[] nums = {1, 2, 3, 5, 6, 7};
        // 基本有序情况排序数组
//        int[] nums = {3, 2, 1, 5, 6, 7};
        sort(nums);
    }

    private static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
        System.out.println("");
    }
}

/**
 * 程序运行结果：
 * <pre>
 * 冒泡排序开始··········[6, 5, 4, 3, 2, 1]
 *
 * 第 1 躺排序 ----
 *     6 和 5 交换
 *     6 和 4 交换
 *     6 和 3 交换
 *     6 和 2 交换
 *     6 和 1 交换
 *   第 1 躺排序后的结果：[5, 4, 3, 2, 1, 6]
 *
 * 第 2 躺排序 ----
 *     5 和 4 交换
 *     5 和 3 交换
 *     5 和 2 交换
 *     5 和 1 交换
 *   第 2 躺排序后的结果：[4, 3, 2, 1, 5, 6]
 *
 * 第 3 躺排序 ----
 *     4 和 3 交换
 *     4 和 2 交换
 *     4 和 1 交换
 *   第 3 躺排序后的结果：[3, 2, 1, 4, 5, 6]
 *
 * 第 4 躺排序 ----
 *     3 和 2 交换
 *     3 和 1 交换
 *   第 4 躺排序后的结果：[2, 1, 3, 4, 5, 6]
 *
 * 第 5 躺排序 ----
 *     2 和 1 交换
 *   第 5 躺排序后的结果：[1, 2, 3, 4, 5, 6]
 *
 * 总共排序 5 躺
 * 冒泡排序结束·····················
 * </pre>
 */
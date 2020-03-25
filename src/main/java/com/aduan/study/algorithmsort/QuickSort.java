package com.aduan.study.algorithmsort;

import java.util.Arrays;

/**
 * 排序算法之 -- 快速排序算法：分治法
 * <p>
 * 描述：快速排序算法是由冒泡排序算法演变而来的，因为采用了分治法的思维，效率上比冒泡排序算法高效。
 *
 * <p>
 * 核心思想：和冒泡排序一样，通过元素的逐个比较与交换来达到排序的目的，不同点是，快排是采用分治法，
 * 递归调用，选取基准元素 pivot，把数组拆分成两份，左边的比 pivot 小，右边的比 pivot 大。
 * <p>
 * 排序过程：
 * <pre>
 *     1、选取基准元素 pivot ：最简单是选取第一个元素；为了解决遇到最坏情况下（数组完全有序），可以随机选取一个元素。
 *     2、元素的移动：小于 pivot 的移动到左边，大于 pivot 的移动到右边。1). 挂坑法、2). 指针交换法。
 * </pre>
 *
 * <p>
 * 时间复杂度：最坏情况下 O(n^2)；平均时间复杂度：O(NLogN)
 *
 * @author duanjun
 * @date 2020.03.24
 * @see 参考文章 [程序员小灰] {@link https://mp.weixin.qq.com/s/PQLC7qFjb74kt6PdExP8mw}
 */
public class QuickSort {
    public static int total = 0;

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 3, 2, 1};
//        int[] nums = {3, 2, 4, 5, 1, 6};
        System.out.println("需要排序的数组：" + Arrays.toString(nums));
        sort(nums, 0, nums.length - 1);
        System.out.println("总交换次数：" + total);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int[] nums, int left, int right) {
        // 递归结束条件：left大等于right的时候
        if (left >= right) {
            return;
        }

        System.out.println("待排序下标：left=" + left + " right=" + right + " 需要排序的子数组：" + Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));

        // 得到基准元素位置
        // 挂坑法
//        int pivotIndex = partition_hangHole(nums, left, right);
        // 指针交换法
        int pivotIndex = partition_pointExchange(nums, left, right); //
        System.out.println("\t排序后拆分的子数组为："
                + Arrays.toString(Arrays.copyOfRange(nums, left, pivotIndex)) + "   "
                + Arrays.toString(Arrays.copyOfRange(nums, pivotIndex + 1, right + 1)));
        // 用分治法递归数列的两部分
        sort(nums, left, pivotIndex - 1);
        sort(nums, pivotIndex + 1, right);
    }

    /**
     * 【挖坑法】
     * <p>
     * 缺点：交换次数比较多，效率上没有 指针交换法 高。
     *
     * @return
     */
    private static int partition_hangHole(int[] arr, int startIndex, int endIndex) {
        // 取基准元素：采用最简单的办法 - 直接取第一个元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        // 记录坑的位置 -- 初始等于 pivot 的位置
        int index = startIndex;

        // 大循环在左右指针重合或者交错时结束
        while (left < right) {
            // 处理 right 指针：从右向左进行比较
            while (left < right) {
                // 若 right 指针元素小于 pivot
                if (arr[right] < pivot) {
                    // 把 left 位置的元素赋值为 right 位置的元素
                    arr[left] = arr[right];
                    // 记录新的坑位为 right 位置
                    index = right;
                    // left 向右移动一位
                    left++;
                    // 跳出 right 指针的处理 - 接下来处理 left
                    break;
                }
                // 若 right 指针的元素大于 pivot , 则 right 指针向左移动
                right--;
            }

            // 处理 left 指针：从左向右进行比较
            while (left < right) {
                // 若 left 指针元素大于 pivot
                if (arr[left] > pivot) {
                    // 把 right 位置的元素赋值为 left 位置的元素
                    arr[right] = arr[left];
                    // 记录新的坑位为 left 位置
                    index = left;
                    // right 向左移动一位
                    right--;
                    // 跳出 left 指针的处理 - 接下来处理 right
                    break;
                }
                // 若 left 指针的元素 小于 pivot , 则 left 指针向右移动
                left++;
            }
        }
        // 把坑的位置元素赋值为 pivot
        arr[index] = pivot;
        // 返回坑的位置为拆分数组的位置
        return index;
    }

    /**
     * 【指针交换法】
     * 步骤：
     * <pre>
     *      1.选取第一个元素为基准元素 pivot，记录开始索引为 left 指针，结束索引为 right 指针
     *      2.只要 left != right，即重合，循环：
     *          1).先比较 right 指针元素 >= pivot , right 指针左移动
     *          2).再比较 left 指针元素 <= pivot , left 指针右移动
     *          3).若 left < right 时，元素交换，继续循环...
     *      3.直到 left 和 right 重合时，用 pivot 和 left 元素交换
     *      4.返回 left 的值（后续在用数组 left 两边的子数组递归调用排序）
     * </pre>
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partition_pointExchange(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        System.out.println("\t选取基准元素为：" + pivot);
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            System.out.println("\t比较right指针：");
            //控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                System.out.println("\t\t" + arr[right] + " > " + pivot + " --> right 指针左移动一位：right=" + (right - 1));
                right--;
            }
            System.out.println("\t比较left指针：");
            //控制right指针比较并右移
            while (left < right && arr[left] <= pivot) {
                System.out.println("\t\t" + arr[left] + " <= " + pivot + " --> left 指针右移动一位：left=" + (left + 1));
                left++;
            }
            //交换left和right指向的元素
            if (left < right) {
                System.out.println("\t交换left 与 right的值：" + arr[left] + " <==> " + arr[right]);
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
                total++;
                System.out.println("\t交换后数组变为：" + Arrays.toString(arr) + "\n");
            }
        }
        System.out.println("\tleft 和 right 重合了，left=" + left + " right=" + right + " --> 与基准元素交换：" + pivot + " <==> " + arr[left]);
        //pivot和指针重合点交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        total++;
        return left;
    }

}

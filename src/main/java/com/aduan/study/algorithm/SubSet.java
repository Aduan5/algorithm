package com.aduan.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 【子集】
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * @Author DuanJun
 * @Date 2019/12/13 10:25
 */
public class SubSet {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return subsets;

        int size = nums.length;
        int n = 1 << size;

        for (int i = 0; i < n; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                System.out.println(i + " >> " + j + " = " + (i >> j) + " & 1 = " + ((i >> j) & 1));
                if (((i >> j) & 1) == 1) {
                    cur.add(nums[j]);
                }
            }
            System.out.println("   -->  " + cur);
            subsets.add(cur);
        }
        return subsets;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(subsets(new int[]{1, 2, 3})));
    }
}

package com.aduan.study.algorithm;

/**
 * @Description 【寻找两个有序数组的中位数】
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * @Author DuanJun
 * @Date 2019/11/28 10:59
 */
public class FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1l = nums1 != null ? nums1.length : 0;
        int nums2l = nums2 != null ? nums2.length : 0;
        int sumLen = nums1l + nums2l;
        int[] nums = new int[sumLen];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < sumLen; i++) {
            if (index1 + 1 > nums1l) {
                nums[i] = nums2[index2++];
                continue;
            }
            if (index2 + 1 > nums2l) {
                nums[i] = nums1[index1++];
                continue;
            }
            if (nums1[index1] < nums2[index2]) {
                nums[i] = nums1[index1++];
            } else {
                nums[i] = nums2[index2++];
            }
        }

        if (sumLen % 2 != 0)
            return nums[sumLen / 2];

        int i = sumLen / 2;
        return (nums[i - 1] + nums[i]) * 1.0 / 2;
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}

package com.aduan.study.algorithm;

/**
 * 分治算法 -- 寻找假银币：
 * 一个袋子里面有N枚银币，其中一枚是假的，唯一区别是假的真的轻一点，找到该枚假币
 */
public class FenZhiAlgorithm {

    /**
     * 查找假银币
     *
     * @param coin
     * @param low
     * @param high
     * @return
     */
    public static int findFalseCoin(int[] coin, int low, int high) {
        // 临界点：当只剩下两个银币时，直接比较
        if (low + 1 == high) {
            if (coin[low] < coin[high]) {
                return low + 1;
            } else {
                return high + 1;
            }
        }

        int leftSum = 0, rightSum = 0;
        // 银币个数为偶数时
        if ((high - low + 1) % 2 == 0) {
            // 分别对前半段和后半段求和
            for (int i = low; i <= (low + (high - low) / 2); i++) {
                leftSum += coin[i];
            }
            for (int i = (low + (high - low) / 2 + 1); i <= high; i++) {
                rightSum += coin[i];
            }
            if (leftSum < rightSum) {
                return findFalseCoin(coin, low, low + (high - low) / 2);
            }
            if (leftSum > rightSum) {
                return findFalseCoin(coin, low + (high - low) / 2 + 1, high);
            }
            return -1;
        }
        // 银币个数为奇数
        else {
            // 分别对前半段和后半段求和
            for (int i = low; i <= (low + (high - low) / 2 - 1); i++) {
                leftSum += coin[i];
            }
            for (int i = low + (high - low) / 2 + 1; i <= high; i++) {
                rightSum += coin[i];
            }
            if (leftSum < rightSum) {
                return findFalseCoin(coin, low, (low + (high - low) / 2 - 1));
            }
            if (leftSum > rightSum) {
                return findFalseCoin(coin, (low + (high - low) / 2 + 1), high);
            }
            // 计算中间剩下的一个是否为假币
            if (coin[low] != coin[low + (high - low) / 2]) {
                return low + (high - low) / 2 + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // 0表示假银币，1表示真银币
        int[] coin = {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1};
        int n = coin.length;
        System.out.println("银币总个数：" + n
                + "，假银币为第" + findFalseCoin(coin, 0, n - 1) + "个");
    }
}

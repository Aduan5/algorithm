package com.aduan.study.algorithm;

import java.util.BitSet;
import java.util.PriorityQueue;

/**
 * <h2>如何找出排名前 500 的数？</h2>
 * 题目描述
 * 有 20 个数组，每个数组有 500 个元素，并且有序排列。如何在这 20*500 个数中找出前 500 的数？
 * <p>
 * 解答思路:
 * 对于 TopK 问题，最常用的方法是使用堆排序。对本题而言，假设数组降序排列，可以采用以下方法：
 * 首先建立大顶堆，堆的大小为数组的个数，即为 20，把每个数组最大的值存到堆中。
 * 接着删除堆顶元素，保存到另一个大小为 500 的数组中，然后向大顶堆插入删除的元素所在数组的下一个元素。
 * 重复上面的步骤，直到删除完第 500 个元素，也即找出了最大的前 500 个数。
 * <p>
 * 为了在堆中取出一个数据后，能知道它是从哪个数组中取出的，从而可以从这个数组中取下一个值，
 * 可以把数组的指针存放到堆中，对这个指针提供比较大小的方法。
 */
public class FindTopNTest {

    public static void main(String[] args) {
//        int[][] data = {
//                {29, 17, 14, 2, 1},
//                {19, 17, 16, 15, 6},
//                {30, 25, 20, 14, 5}
//        };
//
//        int[] top = getTop(data);
//        System.out.println(Arrays.toString(top));

        int[] array = {3, 8, 5, 7, 1};
        BitSet bitSet = new BitSet(5);

        for (int i = 0; i < array.length; i++) {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.get(6));
        System.out.println(bitSet.get(8));
        bitSet.stream().forEach(e -> System.out.print(e));
    }

    private static int[] getTop(int[][] data) {
        int rowLength = data.length;
        int columnLength = data[0].length;

        //创建一个和columnLength大小的数组，用于存放结果
        int[] result = new int[columnLength];

        // 用PriorityQueue实现从大到小的堆排序
        PriorityQueue<DataWithSource> maxHeap = new PriorityQueue();

        // 将每个数组的最大一个元素放入堆中
        for (int i = 0; i < rowLength; ++i) {
            DataWithSource d = new DataWithSource(data[i][0], i, 0);
            maxHeap.add(d);
        }

        int num = 0;
        while (num < columnLength) {
            // 删除堆顶元素
            DataWithSource d = maxHeap.poll();
            result[num++] = d.getValue();
            if (num >= columnLength) {
                break;
            }

            d.setValue(data[d.getSource()][d.getIndex() + 1]);
            d.setIndex(d.getIndex() + 1);
            maxHeap.add(d);
        }
        return result;
    }

    private static class DataWithSource implements Comparable<DataWithSource> {
        /**
         * 数值
         */
        private int value;

        /**
         * 记录数值来源的数组
         */
        private int source;

        /**
         * 记录数值数值的下标
         */
        private int index;

        public DataWithSource(int value, int source, int index) {
            this.value = value;
            this.source = source;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        /**
         * 由于 PriorityQueue 使用小顶堆来实现，这里通过修改
         * 两个整数的比较逻辑来让 PriorityQueue 变成大顶堆
         */
        @Override
        public int compareTo(DataWithSource o) {
            return 0;
        }
    }
}

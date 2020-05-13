package com.aduan.study.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：102. 二叉树的层序遍历
 * 难度：中等
 * 描述：给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <pre>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * </pre>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 *
 * @author dj
 * @since 2020-05-13 10:06
 */
public class _102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15), new TreeNode(7)));

        List<List<Integer>> lists = new _102_BinaryTreeLevelOrderTraversal().levelOrder(root);
        System.out.println(lists);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        // 封装TreeNode 节点的列表 -- 用来实现层次遍历
        List<TreeNode> nodes = new ArrayList<>();

        // 首先把 根 元素放入nodes中
        nodes.add(root);

        // 循环处理 nodes 列表 -- 直到size == 0
        while (nodes.size() > 0) {
            // 封装每一层元素的值
            List<Integer> list = new ArrayList<>();
            // 需要先保存 size -- 因为会动态改变
            int size = nodes.size();
            // 循环 nodes 列表 -- 封装每一层元素的值
            for (int i = 0; i < size; i++) {
                // 取出第0个元素 -- 并删除它
                TreeNode remove = nodes.remove(0);
                // 添加到每一层列表的list中
                list.add(remove.val);
                // 关键的步骤：若left不为空 -- 则添加的nodes列表后面 -- 下一层while循环使用
                if (remove.left != null) {
                    nodes.add(remove.left);
                }
                // 关键的步骤：若right不为空 -- 则添加的nodes列表后面 -- 下一层while循环使用
                if (remove.right != null) {
                    nodes.add(remove.right);
                }
            }
            lists.add(list);
        }

        return lists;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


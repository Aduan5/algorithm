package com.aduan.study.leetcode.binarytree;

/**
 * 题目：572. 另一个树的子树
 * 难度：简单
 * 描述：给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * <pre>
 *      示例 1:
 *          给定的树 s:
 *                   3
 *                  / \
 *                 4   5
 *                 / \
 *               1   2
 *          给定的树 t：
 *
 *                  4
 *                 / \
 *                1   2
 *          返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 *     示例 2:
 *          给定的树 s：
 *                  3
 *                 / \
 *                4   5
 *               / \
 *              1   2
 *                 /
 *                0
 *          给定的树 t：
 *
 *                  4
 *                 / \
 *                1   2
 *          返回 false。
 *
 * </pre>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 *
 * @author dj
 * @since 2020-05-07 11:59
 */
public class _572_SubtreeOfAnotherTree {

    public static void main(String[] args) {
        _572_SubtreeOfAnotherTree subtree = new _572_SubtreeOfAnotherTree();

        // 样例1
        TreeNode s1 = new TreeNode(3,
                new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));

        // 样例2
        TreeNode s2 = new TreeNode(3,
                new TreeNode(4, new TreeNode(1),
                        new TreeNode(2, new TreeNode(0), null)), new TreeNode(5));

        // 子树
        TreeNode t = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        System.out.println(subtree.isSubtree(s1, t));
        System.out.println(subtree.isSubtree(s2, t));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    /**
     * 深度优先搜索 -- 暴力匹配判断
     */
    public boolean dfs(TreeNode s, TreeNode t) {
        // 若匹配到 s 节点为 null 了，则代表已经不相等了
        if (s == null) {
            return false;
        }
        // 检查是否相等 和 递归调用判断左、右子树
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
        // 当已检查到 s 为空了 并且 t 为空了，则代表已匹配到子节点了，因此 t 是 s 的子树
        if (s == null && t == null) {
            return true;
        }
        // 若 s 为空了 并且 t 不为空 -- 则没有进一步比较的节点，因此 t 不是 s 的子树
        // 若 s 不为空 并且 t 为空了 -- 则没有进一步比较的节点，因此 t 不是 s 的子树
        // 若 s 的值 与 t 的值不相等，因此 t 不是 s 的子树
        if ((s == null && t != null) || (s != null && t == null) || s.val != t.val) {
            return false;
        }

        // 到这一步时：s.var == t.var -- 因此需要进一步比较左、右子树
        // 同时移动左 或 右 指针 -- 并且 -- 左、右比较都等时，t 才是 s 的子树
        return check(s.left, t.left) && check(s.right, t.right);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }
}


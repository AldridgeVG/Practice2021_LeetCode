package LeetCode_100HotQ.Simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 */
public class BinaryTreeMiddleTraverse {
    public static class TreeNode {
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

    public static void main(String[] args) {
        TreeNode data = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        System.out.println(inorderTraversal(data));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        midTraverse(root, ret);
        return ret;
    }

    public static void midTraverse(TreeNode node, List<Integer> res) {
        if (node == null) return;
        midTraverse(node.left, res);
        res.add(node.val);
        midTraverse(node.right, res);
    }
}
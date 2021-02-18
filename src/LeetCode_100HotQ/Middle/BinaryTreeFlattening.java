package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeFlattening {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 当前层
            List<Integer> curLevel = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            // 层序遍历，根出队后左右分别入队
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(curLevel);
        }
        return ret;
    }

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
        TreeNode data = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        flatten(data);
        System.out.println(levelOrder(data));
    }

    public static void flatten(TreeNode root) {
        if (root == null) return;
        List<TreeNode> res = new ArrayList<>();
        preorderTraverse(root, res);

        TreeNode pos =  root;
        for (int i = 1; i < res.size(); i++) {
            pos.left = null;
            pos.right = res.get(i);
            pos = pos.right;
        }
    }

    public static void preorderTraverse(TreeNode node, List<TreeNode> res) {
        if (node == null) return;
        res.add(node);
        preorderTraverse(node.left, res);
        preorderTraverse(node.right, res);
    }
}

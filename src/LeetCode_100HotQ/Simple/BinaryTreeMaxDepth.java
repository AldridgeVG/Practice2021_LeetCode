package LeetCode_100HotQ.Simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，返回它的最大深度 3 。
 */
public class BinaryTreeMaxDepth {
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
        TreeNode data = new TreeNode(1, new TreeNode(2, new TreeNode(2), null), new TreeNode(2, new TreeNode(2), null));
        System.out.println(maxDepth(data));
        System.out.println(maxDepth1(data));
    }

    // 记录BFS层序遍历层数
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            // 当前层
            int currentLevelSize = queue.size();
            // 层序遍历，根出队后左右分别入队
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    // DFS左右子树深度最大值
    public static int maxDepth1(TreeNode root){
        if(root == null) {
            return 0;
        }
        // recursive DFS
        else {
            int left = maxDepth1(root.left);
            int right = maxDepth1(root.right);
            return Math.max(left, right) + 1;
        }
    }
}
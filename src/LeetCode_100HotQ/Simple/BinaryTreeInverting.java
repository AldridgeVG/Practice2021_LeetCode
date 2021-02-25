package LeetCode_100HotQ.Simple;

/**
 * 反转二叉树
 */
public class BinaryTreeInverting {
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

    // 递归执行：对左右子树分别递归执行翻转后，再交换左右子树
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode invertedLeft = invertTree(root.left);
        TreeNode invertedRight = invertTree(root.right);
        root.left = invertedRight;
        root.right = invertedLeft;
        return root;
    }
}

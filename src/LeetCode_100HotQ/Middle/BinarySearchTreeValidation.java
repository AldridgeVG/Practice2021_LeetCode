package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class BinarySearchTreeValidation {
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

    public static boolean ret = true;

    public static void main(String[] args) {
        //System.out.println(isValidBST(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
        System.out.println(isValidBST(new TreeNode(1, new TreeNode(1), null)));
    }

    // 中序遍历是否升序？
    public static boolean isValidBST(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        midTraverseCheck(root, tree);
        return ret;
    }

    public static void midTraverseCheck(TreeNode node, List<Integer> tree) {
        if (node == null) return;
        midTraverseCheck(node.left, tree);
        if (!tree.isEmpty() && node.val <= tree.get(tree.size() - 1)) {
            ret = false;
            return;
        } else {
            tree.add(node.val);
        }
        midTraverseCheck(node.right, tree);
    }
}

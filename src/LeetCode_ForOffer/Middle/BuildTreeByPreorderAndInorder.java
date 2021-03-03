package LeetCode_ForOffer.Middle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class BuildTreeByPreorderAndInorder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        System.out.println(buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // spec cond
        if (preorder.length != inorder.length || preorder.length == 0) return null;
        // norm cond
        int rootVal = preorder[0];
        int rootPos = Arrays.stream(inorder).boxed().collect(Collectors.toList()).indexOf(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, rootPos + 1), Arrays.copyOfRange(inorder, 0, rootPos));
        root.right = buildTree(Arrays.copyOfRange(preorder, rootPos + 1, preorder.length), Arrays.copyOfRange(inorder, rootPos + 1, inorder.length));
        return root;
    }
}

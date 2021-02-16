package LeetCode_100HotQ.Simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 */
public class BinaryTreeSymmetricCheck {
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
        System.out.println(isSymmetric(data));
    }

    public static boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    /**
     * 我们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，
     * pp 指针和 qq 指针一开始都指向这棵树的根，随后 pp 右移时，qq 左移，pp 左移时，qq 右移。
     * 每次检查当前 pp 和 qq 节点的值是否相等，如果相等再判断左右子树是否对称。
     */
    public static boolean check(TreeNode p, TreeNode q) {
        // 找到对称的树叶的左右孩子 或 落叶点
        if (p == null && q == null) {
            return true;
        }
        // 不对称返回false
        if (p == null || q == null) {
            return false;
        }
        // 双指针向两个方向对称移动
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}

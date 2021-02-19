package LeetCode_100HotQ.Middle;

/**
 * 寻找一棵二叉树里两个节点的最近公共祖先（可以为他们自己
 */
public class BinaryTreeLowestCommonAncestor {
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

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    public static void main(String[] args) {
        System.out.println(LCA(3, 4, new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7)))));
    }

    public static TreeNode LCA(int n1, int n2, TreeNode root) {
        // 若一直向下直到越过叶节点则返回 null，找到根为 n1, n2 之一则返回 n1 / n2，表示当前子树存在 n1 / n2
        if (root == null || root.val == n1 || root.val == n2) return root;

        // 在 root 不是 LCA 的前提下属于暂未找到，递归查找左右子树
        TreeNode left = LCA(n1, n2, root.left);
        TreeNode right = LCA(n1, n2, root.right);

        // 递归结果整合
        // 左右子树均未找到则返回 null
        if (left == null && right == null)
            return null;
        // 左右子树分别含有 n1/ n2, 找到LCA
        else if (left != null && right != null)
            return root;
        // 左右子树之一不为空，说明不为空的那个结果为下面传递上来的 LCA
        else
            return left == null ? right : left;
    }
}

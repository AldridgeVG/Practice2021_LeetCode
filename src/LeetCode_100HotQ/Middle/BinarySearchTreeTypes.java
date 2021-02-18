package LeetCode_100HotQ.Middle;

public class BinarySearchTreeTypes {
    public static void main(String[] args) {
        System.out.println(numTrees(4));
    }

    public static int numTrees(int n) {
        /*
         * dp[n] 表示 n 个节点的异构二叉搜索树个数
         * f(i,n) 表示以 i 为根构建的长为 n 的异构二叉搜索树个数
         *
         * 显然 dp[n] = sigma(i=1..n) f(i,n)
         * 由于以 i 为根时，左右子树分别为 1..i-1 (i-1个数) 和 i+1..n (n-i个数) 构建
         * 结合起来递推公式为：
         *
         * dp[n] = sigma(i=1..n) dp[i-1]*dp[n-i]
         *
         * 可见计算dp[n]只依赖于dp[0]..dp[n-1]的值
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int rn = 2; rn <= n; rn++) {
            for (int i = 1; i <= rn; i++) {
                dp[rn] += dp[i - 1] * dp[rn - i];
            }
        }
        return dp[n];
    }
}

package LeetCode_100HotQ.Middle;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 */
public class UniquePath {
    public static void main(String[] args) {
        System.out.println(uniquePaths(10, 10));
        System.out.println(uniquePaths(10, 10));
    }

    /**
     * M*N的方格里，一定会走 m+n-2 步，其中m-1步为向下走，n-1步为向右走，计算排列即可
     * <p>
     * -- 数据太大溢出
     */
    public static int uniquePaths(int m, int n) {
        // spec cond
        if (m == 0 || n == 0) return 0;
        if (m < 2 || n < 2) return 1;

        // norm cond
        long ans = 1;
        for (int i = 0; i < Math.min(m - 1, n - 1); i++) {
            ans *= m + n - 2 - i;
            ans /= i + 1;
        }
        return (int) ans;
    }

    /**
     * dp[i][j] means the max number of paths to (i,j)
     */
    public static int uniquePathsDP(int m, int n) {
        // spec cond
        if (m == 0 || n == 0) return 0;
        if (m < 2 || n < 2) return 1;

        // norm cond
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

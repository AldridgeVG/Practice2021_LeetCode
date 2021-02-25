package LeetCode_100HotQ.Middle;

import java.util.Arrays;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
public class DivideIntoFSN {
    public static void main(String[] args) {
        System.out.println(numSquares(27)); // exp 3
        System.out.println(numSquares(17)); // exp 2
    }

    /**
     * DP :
     * numSquares(n) = min(numSquares(n-k) + 1)   ∀ k ∈ square numbers
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 虚拟的 dp[0]=0 来简化逻辑
        dp[0] = 0;

        // 提前计算小于 n 的所有平方数
        int max_square_index = (int) Math.sqrt(n) + 1;
        int[] square_nums = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        // dp[n] = min( dp[n-k] + 1 ) , k in [square numbers < n]
        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; s++) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }
}

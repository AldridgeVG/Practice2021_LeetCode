package LeetCode_100HotQ.Middle;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class FindLengthOfLIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;

        int maxRet = 0;
        // dp[i]表示以nums[I]结束的递增子序列长度，最大值可能在任何位置
        int[] dp = new int[len];
        // 单个为递增
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            int max = 0;
            // 每一个之前的位置均需要判断，取最大值
            for (int j = 0; j < i; j++) {
                // 比之前这个位置大，序列为之前长度加一
                if (nums[i] > nums[j]) {
                    dp[i] = dp[j] + 1;
                }
                // 比之前长度小，为一个新序列起点，长度为1
                else {
                    dp[i] = 1;
                }
                // 取每一个可能的前位置的长度的最大值
                max = Math.max(max, dp[i]);
            }
            dp[i] = max;
            // 取以所有可能位置结束的递增序列长度最大值
            maxRet = Math.max(maxRet, dp[i]);
        }
        return maxRet;
    }
}

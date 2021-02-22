package LeetCode_100HotQ.Middle;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class StealFromNonAdjacentHouse {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }

    public static int rob(int[] nums) {
        int length = nums.length;

        // spec cond
        if (length == 0) return 0;
        if (length == 1) return nums[0];

        // norm cond
        // dp[i] mns the max number you can get if you end with house i
        int dp[] = new int[length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < length; i++) {
            // 最后抢劫第 i 间房的情况下，上一次抢劫的除了第 i-1 间外只要下标比 i 小则均有可能（e.g. [2,1,1,2]，应该抢 2(0=3-3)，2(3)）
            for (int j = 0; j <= i - 2; j++) {
                dp[i] = Math.max(dp[i], nums[i] + dp[j]);
            }
        }
        return Math.max(dp[length - 1], dp[length - 2]);
    }
}

package LeetCode_100HotQ.Middle;

import java.util.Arrays;

public class CanPartitionIntoSameSumParts {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 6, 6, 6, 21}));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int cur : nums) {
            sum += cur;
        }
        if (sum % 2 == 1) return false;
        int halfSum = sum / 2;
        int bigest = nums[nums.length - 1];
        if (bigest > halfSum) return false;
        if (bigest == halfSum) return true;
        int remain = halfSum - bigest;
        return canMakeUp(Arrays.copyOfRange(nums, 0, nums.length - 1), remain);
    }

    public static boolean canMakeUp(int[] elems, int target) {
        if (elems.length == 0) return false;
        if (elems[0] == target) return true;
        return canMakeUp(Arrays.copyOfRange(elems, 1, elems.length), target - elems[0]) ||
                canMakeUp(Arrays.copyOfRange(elems, 1, elems.length), target);
    }

    //----------------------------------------------------------------------//

    /**
     * 动态规划算法，改自01背包问题
     *
     * 创建二维数组 boolean[][] dp，包含 n 行 target+1 列，其中 dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于 j。
     * 初始时，dp 中的全部元素都是 false。
     *
     * 在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况:
     * 1. 如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0≤i<n，都有 dp[i][0]=true。
     * 2. 当 i==0 时，只有一个正整数 nums[0] 可以被选取，因此 dp[0][nums[0]]=true。
     *
     * 对于 i>0 且 j>0 的情况，确定 dp[i][j] 的值需要分别考虑以下两种情况。
     *
     * 如果 j≥nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有 dp[i][j]=true。
     *
     * 如果不选取 nums[i]，则 dp[i][j]=dp[i−1][j]；
     * 如果选取 nums[i]，则 dp[i][j]=dp[i−1][j−nums[i]]。
     * 如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 \nums[i]，因此有 dp[i][j]=dp[i−1][j]。
     *
     * 状态转移方程如下：
     *
     *               |dp[i−1][j] ∣ dp[i−1][j−nums[i]],    j≥nums[i]
     * dp[i][j] =  <|
     *              |dp[i−1][j],                          j<nums[i]
     * ​
     * 最终得到 dp[n−1][target] 即为答案。
     */
    public static boolean canPartitionDP(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        // 总和为奇数不可分
        if (sum % 2 != 0) {
            return false;
        }
        // 单个数大于半和也不可分
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }

        // DP
        boolean[][] dp = new boolean[n][target + 1];
        // init dp
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        // calc dp
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
}

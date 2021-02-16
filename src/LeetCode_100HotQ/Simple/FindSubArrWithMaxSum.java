package LeetCode_100HotQ.Simple;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class FindSubArrWithMaxSum {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums) {
        // spec cond
        if (nums.length == 0) return 0;
        // norm cond
        int preSum = 0;
        int maxSum = nums[0];
        for (int cur : nums) {
            /*
             * 重要：对于任意一个数，目标子数组都有可能以其结束
             *
             * preSum为直到cur这个数的最大子数组和，所以无论如何都要包含cur
             * 那么包含cur就包括 只有cur自己一个数的子数组和比较大 以及 cur之前的最大和加上cur的子数组和比较大两种情况
             *
             * 而如果preSum取了cur，相当于舍弃了之前的最大和，代表cur足够大去挑战maxSum，
             */
            preSum = Math.max(cur, preSum + cur);
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum;
    }
}

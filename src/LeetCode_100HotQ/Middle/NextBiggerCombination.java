package LeetCode_100HotQ.Middle;

import java.util.Arrays;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class NextBiggerCombination {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3};
        nextPermutation(data);
        System.out.println(Arrays.toString(data));
        data = new int[]{6, 5, 4, 3, 2, 3, 2, 1};
        nextPermutation(data);
        System.out.println(Arrays.toString(data));
    }

    public static void nextPermutation(int[] nums) {
        // spec cond - nothing to do
        if (nums == null || nums.length < 2) {
        }
        // spec cond - length 2 - switch 66->66, 65->56, 56->65
        else if (nums.length == 2) {
            int tmp = nums[0];
            nums[0] = nums[1];
            nums[1] = tmp;
        }
        // norm cond - length > 2
        else {
            int len = nums.length;
            int pos = -1;
            // find first number smaller than his right number e.g. 1 2 3 6 (4) 5 3
            for (int i = len - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    pos = i;
                    break;
                }
            }

            // 1. failed to find, strict descending e.g. 6 5 4 3 3 2 1, sort array to ascending order
            if (pos == -1) Arrays.sort(nums);
                // 2. found, SWITCH  the smallest number bigger than nums[i] on his right  WITH  nums[i] , then sort the right
                // e.g. 6 5 4 3 (1) 3 [2] 1
            else {
                int pos1 = 0;
                // on pos's right, it's strict descending, so look one by one to get the last one bigger than nums[pos]
                for (int i = pos + 1; i < len; i++) {
                    if (nums[i] > nums[pos]) pos1 = i;
                }
                // switch pos and pos1
                int tmp = nums[pos];
                nums[pos] = nums[pos1];
                nums[pos1] = tmp;
                // sort the right
                Arrays.sort(nums, pos + 1, len);
            }
        }
    }
}

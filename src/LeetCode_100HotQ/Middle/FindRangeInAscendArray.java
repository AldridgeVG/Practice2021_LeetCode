package LeetCode_100HotQ.Middle;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 */
public class FindRangeInAscendArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 10}, 6)));
    }

    /**
     * O(n) and O(1)
     */
    public static int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int[] range = new int[]{-1, -1};
        // spec cond
        if (len == 0) return range;
        if (len == 1) return nums[0] == target ? new int[]{0, 0} : range;

        // norm cond
        for (int i = 0; i < len; i++) {
            // failure
            if (nums[i] > target) break;
            // found
            if (nums[i] == target) {
                range[0] = i;
                for (range[1] = i; range[1] < len; range[1]++, i++) {
                    if (nums[range[1]] > target) break;
                }
                range[1]--;
            }
            //do nothing
        }
        return range;
    }
}

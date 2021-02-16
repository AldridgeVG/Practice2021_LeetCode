package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class AllZeroSumCombination {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 0, 0, 1, 2, -1, 4, 3, -2}));
    }

    // - - +
    // - + +
    // 0 0 0
    // - 0 +
    // start with number <= 0
    public static List<List<Integer>> threeSum(int[] nums) {
        // init list
        List<List<Integer>> ret = new ArrayList<>();

        // spec1
        if (nums == null || nums.length <= 2) return ret;

        // Q_sort O(nlogn)
        Arrays.sort(nums);

        // spec2
        if (nums[0] > 0 || nums[nums.length - 1] < 0) return ret;

        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            // 第一个数大于 0，后面的数都比它大，肯定不成立了，但有可能三个 0
            if (nums[i] > 0) break;
            // 以第一个数为基数的情况下，去掉重复情况
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                // 找到符合
                if (nums[left] + nums[right] == target) {
                    ret.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    // 由于确定了一个基数，剩下两个数（left、right）任意一个都不能出现重复，否则三数均重复；由于数组有序，不会出现LR交换的情况
                    // 首先无论如何先要进行一次加减操作
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
                // 未找到符合且总数太小，即负数太小
                else if (nums[left] + nums[right] < target) {
                    left++;
                }
                // 未找到符合且总和太大， 即正数太大
                else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ret;
    }
}

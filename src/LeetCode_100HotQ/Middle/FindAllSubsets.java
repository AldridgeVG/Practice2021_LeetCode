package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class FindAllSubsets {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3, 4}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        int n = nums.length;
        // from 0 to 2^n - 1,
        for (int mask = 0; mask < (1 << n); mask++) {
            sub.clear();
            // for the n number, it's position in mask is either 0 or 1, signify its existence
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0)
                    sub.add(nums[i]);
            }
            ret.add(new ArrayList<>(sub));
        }
        return ret;
    }
}

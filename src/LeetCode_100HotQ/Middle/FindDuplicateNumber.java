package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicateNumber {
    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 6, 4, 5, 5, 2}));
    }

    public static int findDuplicate(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length <= 2) return nums[0];

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return num;
        }
        return 0;
    }
}

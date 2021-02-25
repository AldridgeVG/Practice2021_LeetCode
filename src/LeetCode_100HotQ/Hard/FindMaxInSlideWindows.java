package LeetCode_100HotQ.Hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 */
public class FindMaxInSlideWindows {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k || k == 0) return new int[]{};

        // return List
        List<Integer> retList = new ArrayList<>();
        // store K number
        Deque<Integer> numsQ = new ArrayDeque<>();
        // getFirst
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            numsQ.addLast(nums[i]);
            max = Math.max(max, nums[i]);
        }
        retList.add(max);

        // loop left
        for (int i = k; i < nums.length; i++) {
            numsQ.addLast(nums[i]);
            // 移除最大的，添加后重新计算最大值
            if (numsQ.removeFirst() == max) {
                max = Integer.MIN_VALUE;
                for (Integer cur : numsQ) {
                    max = Math.max(max, cur);
                }
            } else {
                max = Math.max(max, nums[i]);
            }
            retList.add(max);
        }

        // return to array
        return retList.stream().mapToInt(Integer::intValue).toArray();
    }
}

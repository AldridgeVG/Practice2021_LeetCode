package LeetCode_100HotQ.Simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。\
 */
public class FindMajorityInArray {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> content = new HashMap<>();
        for (int i : nums) {
            content.put(i, content.getOrDefault(i, 0) + 1);
        }
        int ret = 0, limit = nums.length / 2;
        for (int i : content.keySet()) {
            if (content.get(i) > limit) ret = i;
        }
        return ret;
    }
}

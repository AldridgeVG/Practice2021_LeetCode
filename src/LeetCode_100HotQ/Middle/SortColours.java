package LeetCode_100HotQ.Middle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 */
public class SortColours {
    public static void main(String[] args) {
        int[] data = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(data);
        System.out.println(Arrays.toString(data));
    }

    // 桶排序
    public static void sortColors(int[] nums) {
        if (nums.length == 0) return;

        Map<Integer, Integer> colourMap = new HashMap<>();
        // 建桶
        colourMap.put(0,0);
        colourMap.put(1,0);
        colourMap.put(2,0);
        for (int cur : nums) {
            colourMap.replace(cur, colourMap.get(cur) + 1);
        }
        Arrays.fill(nums, 0, colourMap.get(0), 0);
        Arrays.fill(nums, colourMap.get(0), colourMap.get(0) + colourMap.get(1), 1);
        Arrays.fill(nums, colourMap.get(0) + colourMap.get(1), nums.length, 2);
    }
}

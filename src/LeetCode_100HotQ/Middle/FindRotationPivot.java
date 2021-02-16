package LeetCode_100HotQ.Middle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
 * <p>
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 */
public class FindRotationPivot {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(searchCOMPLEX(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    /**
     * Simple STL method
     */
    public static int search(int[] nums, int target) {
        int len = nums.length;
        // spec cond
        if (len == 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;

        // norm cond
        List<Integer> arrList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return arrList.indexOf(target);
    }

    /**
     * reorder and B-Search, then repair original order, mind -1.
     */
    public static int searchCOMPLEX(int[] nums, int target) {
        int pos = 0, len = nums.length;
        int[] numsOrdered = new int[len];
        // spec cond
        if (len == 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;

        // norm cond
        // if [3,4,5,1,2], pos = 3 ( min 's index, its new index will be 0 )
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                pos = i + 1;
                break;
            }
        }
        System.arraycopy(nums, pos, numsOrdered, 0, len - pos);
        System.arraycopy(nums, 0, numsOrdered, len - pos, pos);

        int posOrdered = binarySearch(numsOrdered, 0, len - 1, target);
        return posOrdered == -1 ? -1 : (posOrdered + pos) % len;
    }

    public static int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) return binarySearch(nums, left, mid - 1, target);
        else return binarySearch(nums, mid + 1, right, target);
    }
}

package LeetCode_100HotQ.Simple;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class MoveZeroToArrayFront {
    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 0, 0, 2, 0, 12, 4, 0, 0, 5, 6, 0};
        moveZeroes(data);
        System.out.println(Arrays.toString(data));
    }

    // 不断从左到右找到第一个 0 及其之后的第一个非 0 数，并交换，直到在第一个0后找不到非0数（即第一个0之后全是0
    public static void moveZeroes(int[] nums) {
        int len = nums.length;
        if (len < 2) return;

        int firstZero = findFirstZero(nums, 0);
        if (firstZero == -1) return;
        int nextNonZero = findNextNonZero(nums, firstZero + 1);
        while (nextNonZero != -1) {
            int temp = nums[nextNonZero];
            nums[nextNonZero] = 0;
            nums[firstZero] = temp;
            firstZero = findFirstZero(nums, firstZero + 1);
            nextNonZero = findNextNonZero(nums, firstZero + 1);
        }
    }

    public static int findFirstZero(int[] data, int startIndex) {
        for (int i = startIndex; i < data.length; i++) {
            if (data[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int findNextNonZero(int[] data, int startIndex) {
        for (int i = startIndex; i < data.length; i++) {
            if (data[i] != 0) {
                return i;
            }
        }
        return -1;
    }
}
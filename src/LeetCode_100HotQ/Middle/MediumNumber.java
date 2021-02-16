package LeetCode_100HotQ.Middle;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 */
public class MediumNumber {

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 3};
        int[] b = {1, 4, 5};
        System.out.println(findMedianSortedArrays(a, b));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int flag = length % 2;
        int[] reorg = new int[length];
        System.arraycopy(nums1, 0, reorg, 0, nums1.length);
        System.arraycopy(nums2, 0, reorg, nums1.length, nums2.length);
        Arrays.sort(reorg);
        if (flag == 1) {
            return reorg[length / 2];
        } else {
            return ((double) reorg[length / 2] + (double) reorg[length / 2 - 1]) / 2;
        }
    }
}

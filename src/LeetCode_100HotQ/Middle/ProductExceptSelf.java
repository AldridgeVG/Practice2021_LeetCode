package LeetCode_100HotQ.Middle;

import java.util.Arrays;

public class ProductExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len <= 1) return nums;
        int[] ret = new int[len];

//        int front = 1;
//        for (int i = 0; i < len; i++) {
//            if (i > 0) front *= nums[i - 1];
//            int back = 1;
//            for (int j = i + 1; j < len; j++) {
//                back *= nums[j];
//            }
//            ret[i] = back * front;
//        }

        // 优化：保存每个数的前缀和和后缀和
        int[] L = new int[len];
        int[] R = new int[len];
        L[0] = 1;
        R[len - 1] = 1;
        for (int i = 1; i < len; i++) L[i] = L[i - 1] * nums[i - 1];
        for (int i = len - 2; i >= 0; i--) R[i] = R[i + 1] * nums[i + 1];
        for (int i = 0; i < len; i++) {
            ret[i] = L[i] * R[i];
        }
        return ret;
    }
}

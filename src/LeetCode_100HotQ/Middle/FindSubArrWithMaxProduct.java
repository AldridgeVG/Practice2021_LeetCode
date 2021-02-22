package LeetCode_100HotQ.Middle;

public class FindSubArrWithMaxProduct {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }

    public static int maxProduct(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        // 分别保存最大值和最小值
        int maxProduct = Integer.MIN_VALUE, max = 1, min = 1;
        for (int num : nums) {
            // 负数诚意最大的会变成最小的
            if (num < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            // 遇到负数后，保证max为正，min为负
            max = Math.max(max * num, num);
            min = Math.min(min * num, num);
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }
}

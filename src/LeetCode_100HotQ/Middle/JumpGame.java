package LeetCode_100HotQ.Middle;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 */
public class JumpGame {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
    }

    public static boolean canJump(int[] nums) {
        int length = nums.length;
        if (length == 0) return false;
        if (length == 1) return true;

        boolean[] flag = new boolean[length];
        flag[0] = true;
        for (int i = 0; i < length - 1; i++) {
            if (flag[i] == true) {
                for (int j = 1; j <= nums[i]; j++) {
                    if ((i + j) < length) flag[i + j] = true;
                    else break;
                }
            }
        }
        return flag[length - 1];
    }
}

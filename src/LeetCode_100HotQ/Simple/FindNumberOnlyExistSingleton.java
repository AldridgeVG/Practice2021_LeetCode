package LeetCode_100HotQ.Simple;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class FindNumberOnlyExistSingleton {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1, 2, 3, 2, 1, 4, 4}));
    }

    // 位运算性质： a xor a = 0  => a xor a xor b xor b xor c = c
    public static int singleNumber(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        return ret;
    }
}

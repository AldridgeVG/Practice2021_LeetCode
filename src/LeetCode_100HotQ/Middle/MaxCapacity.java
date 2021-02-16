package LeetCode_100HotQ.Middle;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 */
public class MaxCapacity {
    public static void main(String[] args) {
        System.out.println(getMaxArea2Pointer(new int[]{1,3,4,2,8,5,1}));
    }

    public static int getMaxArea(int[] heights) {
        int len = heights.length;
        int ret = 0;
        int area = 0;

        // spec cond
        if (len <= 1) return 0;
        if (len == 2) return Math.min(heights[0], heights[1]);

        // norm
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                area = (j - i) * Math.min(heights[i], heights[j]);
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * double pointer
     */
    public static int getMaxArea2Pointer(int[] heights) {
        int len = heights.length;
        int ret = 0;
        int area = 0;

        // spec cond
        if (len <= 1) return 0;
        if (len == 2) return Math.min(heights[0], heights[1]);

        // norm
        int i = 0, j = len - 1;
        while (i < j) {
            area = Math.min(heights[i], heights[j]) * (j - i);
            ret = Math.max(ret, area);
            if (heights[i] < heights[j]) i++;
            else j--;
        }
        return ret;
    }
}

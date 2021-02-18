package LeetCode_100HotQ.Hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *S
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 */
public class FindMaxSquareInColumns {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea1(new int[]{2, 1, 5, 6, 2, 3}));
    }

    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        // spec cond
        if (len == 0) return 0;
        if (len == 1) return heights[0];

        // 依次调整宽度
        int maxArea = 0;
        for (int width = 1; width <= len; width++) {
            for (int start = 0; start <= len - width; start++) {
                // get min height
                int minHeight = Integer.MAX_VALUE;
                for (int j = start; j < start + width; j++) {
                    minHeight = Math.min(heights[j], minHeight);
                }
                maxArea = Math.max(maxArea, minHeight * width);
            }
        }
        return maxArea;
    }

    public static int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        // spec cond
        if (len == 0) return 0;
        if (len == 1) return heights[0];

        int maxArea = 0;
        // 以每个柱为高
        for (int pos = 0; pos < len; pos++) {
            int height = heights[pos];
            int l = pos, r = pos;
            while (l > 0) {
                l--;
                if (heights[l] < height) {
                    l++;
                    break;
                }
            }
            while (r < len - 1) {
                r++;
                if (heights[r] < height) {
                    r--;
                    break;
                }
            }
            int area = height * (r - l + 1);
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    // 单调栈
    public static int largestRectangleAreaSS(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        // 找以每个柱体为高的矩形面积
        for (int i = 0; i < tmp.length; i++) {
            // 当待入栈的下标的高度小于栈顶元素对应的高度，就要进行特殊操作，也就是前边的有些可以结算了
            // 如新数组【0,2,3,4,1,0】，栈内先正常存放下标0，1，2，3，待下标4(对应高度为1)准备入栈就要特殊操作
            // 依次弹出栈内存放的下标元素3，2，1，弹一次计算一次以当前下标对应的高度h[cur]为高的矩形面积
            //
            // 注！！！ 由于栈内下标的高度是单调递增的，宽度以栈顶为1开始依次加一
            //
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                maxArea = Math.max(maxArea, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

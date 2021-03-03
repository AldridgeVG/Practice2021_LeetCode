package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperature {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73, 77})));
    }

    public static int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if (len == 0) return null;

        // 单调栈
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ret = new int[len];

        for (int i = 0; i < len; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            } else {
                // 出现高温，单减栈内全部出栈
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    ret[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return ret;
    }
}

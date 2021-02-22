package LeetCode_100HotQ.Simple;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素\
 */
public class DesignMinStack {
    private static class MinStack {
        int min;
        Deque<Integer> stack;

        public MinStack() {
            min = Integer.MAX_VALUE;
            stack = new ArrayDeque<>();
        }

        public void push(int x) {
            min = Math.min(min, x);
            stack.push(x);
        }

        // 弹出最小值后重新求最小值
        public void pop() {
            if (min == stack.pop()) {
                min = Integer.MAX_VALUE;
                for (int i : stack) {
                    min = Math.min(min, i);
                }
            }
        }

        public int top() {
            return stack.getFirst();
        }

        public int getMin() {
            return min;
        }
    }
}

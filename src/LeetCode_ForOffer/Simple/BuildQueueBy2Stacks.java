package LeetCode_ForOffer.Simple;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用两个栈实现一个队列
 */
public class BuildQueueBy2Stacks {
    static class CQueue {
        Deque<Integer> inStack;
        Deque<Integer> outStack;

        public CQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        // 出队栈清空后，将入队栈全部倒入出队栈，实现 FILO 反序为 FIFO
        public int deleteHead() {
            if (outStack.isEmpty() && inStack.isEmpty()) return -1;
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

    }

    public static void main(String[] args) {

    }
}

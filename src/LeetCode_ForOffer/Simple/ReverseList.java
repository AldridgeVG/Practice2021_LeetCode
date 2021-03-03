package LeetCode_ForOffer.Simple;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 */
public class ReverseList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode nextL) {
            val = x;
            next = nextL;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(reversePrint(new ListNode(3, new ListNode(2, new ListNode(1))))));
    }

    public static int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] ret = new int[stack.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = stack.pop();
        }
        return ret;
    }
}

package LeetCode_100HotQ.Simple;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 反转一个单链表。
 */
public class ReverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        // spec cond
        if (head == null) return null;

        // norm cond
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        ListNode nHead = new ListNode(stack.pop());
        ListNode cur = nHead;
        while (!stack.isEmpty()) {
            cur.next = new ListNode(stack.pop());
            cur = cur.next;
        }
        return nHead;
    }
}

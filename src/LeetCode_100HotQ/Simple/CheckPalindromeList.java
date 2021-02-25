package LeetCode_100HotQ.Simple;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请判断一个链表是否为回文链表。
 */
public class CheckPalindromeList {
    public static void main(String[] args) {
        System.out.println(isPalindrome(new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))))));
    }

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

    public static boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        Deque<Integer> order1 = new ArrayDeque<>();
        Deque<Integer> order2 = new ArrayDeque<>();
        while (head != null) {
            order1.add(head.val);
            order2.add(head.val);
            head = head.next;
        }
        while (!order1.isEmpty()) {
            if (order1.getFirst().equals(order2.getLast())) {
                order1.removeFirst();
                order2.removeLast();
            } else return false;
        }
        return true;
    }
}

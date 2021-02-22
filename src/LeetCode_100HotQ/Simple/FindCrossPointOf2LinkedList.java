package LeetCode_100HotQ.Simple;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 */
public class FindCrossPointOf2LinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // spec cond
        if (headA == null || headB == null) return null;
        if (headA == headB) return headA;

        // norm cond
        Set<ListNode> nodeA = new HashSet<>();
        while (headA != null) {
            nodeA.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (nodeA.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
}

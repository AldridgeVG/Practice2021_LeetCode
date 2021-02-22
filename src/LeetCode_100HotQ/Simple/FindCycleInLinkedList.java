package LeetCode_100HotQ.Simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。]
 */
public class FindCycleInLinkedList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        Map<ListNode, Integer> visit = new HashMap<>();
        while (head.next != null) {
            if (visit.containsKey(head))
                return true;
            else {
                visit.put(head, 1);
                head = head.next;
            }
        }
        return false;
    }

    /**快慢指针
     *
     *
     * 本方法需要读者对「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解。
     *
     * 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。当「乌龟」和「兔子」从链表上的同一个节点开始移动时，如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；
     * 如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
     *
     * 我们可以根据上述思路来解决本题。具体地，我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。初始时，慢指针在位置 head，而快指针在位置 head.next。
     * 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表
     */
    public static boolean hasCycleFSP(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return false;
        if (head.next.next == head) return true;

        // make sure len > 3
        ListNode rabbit = head, turtle = head;
        do {
            if (rabbit == null || rabbit.next == null) return false;
            rabbit = rabbit.next.next;
            turtle = turtle.next;
        } while (rabbit != turtle);
        return true;
    }
}

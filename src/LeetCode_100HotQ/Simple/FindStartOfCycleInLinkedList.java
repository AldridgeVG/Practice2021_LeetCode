package LeetCode_100HotQ.Simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 */
public class FindStartOfCycleInLinkedList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        // spec cond
        if (head == null || head.next == null) return null;

        // norm cond
        Map<ListNode, Integer> visit = new HashMap<>();
        while (head.next != null) {
            if (visit.containsKey(head))
                return head;
            else {
                visit.put(head, 1);
                head = head.next;
            }
        }
        return null;
    }

    /**
     * O(1)空间复杂度快慢指针法改
     * 我们使用两个指针 fast 与 slow。它们起始都位于链表的头部。随后，low 指针每次向后移动一个位置，而fast 指针向后移动两个位置。如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
     * <p>
     * 如下图所示，设链表中环外部分的长度为 a。\slow 指针进入环后，又走了 b 的距离与 fast 相遇。此时，fast 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b = a+(n+1)b+nc。
     * <p>
     * 根据假设，任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍。因此，我们有
     * <p>
     * a+(n+1)b+nc=2(a+b)  ⟹  a=c+(n−1)(b+c)
     * <p>
     * 有了 a=c+(n−1)(b+c) 的等量关系，我们会发现：从相遇点到入环点的距离加上 n-1 圈的环长，恰好等于从链表头部到入环点的距离。
     * <p>
     * 因此，当发现 slow 与 \fast 相遇时，我们再额外使用一个指针 ptr。起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，ptr 和 slow 会在入环点相遇。
     * （ 这段时间内 ptr 走了 a 距离到入环点 ；而 slow 在距离入环点 b 距离处开始走了 c 后第二次到达入环点，并继续走 n-1 圈后第 n+1 次到达入环点与 ptr 相遇）
     */
    public static ListNode detectCycleFSP(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            // 此时 fast 与 slow 相遇
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}

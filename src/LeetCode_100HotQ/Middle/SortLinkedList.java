package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class SortLinkedList {
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

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        Map<Integer, Integer> map = new HashMap<>();
        while (head != null) {
            if (map.containsKey(head.val)) map.replace(head.val, map.get(head.val) + 1);
            else map.put(head.val, 1);
            head = head.next;
        }

        List<Integer> values = new ArrayList<>();
        for (Integer val : map.keySet()) {
            for (int i = 0; i < map.get(val); i++) values.add(val);
        }
        values.sort(null);

        ListNode nNode = new ListNode(values.get(0));
        ListNode cur = nNode;
        for (int i = 1; i < values.size(); i++) {
            cur.next = new ListNode(values.get(i));
            cur = cur.next;
        }
        return nNode;
    }

    /**
     * 自底向上归并法， O(nlogn) / O(1)
     * <p>
     * 首先求得链表的长度 length，然后将链表拆分成子链表进行合并。
     * <p>
     * 具体做法如下。
     * <p>
     * 用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。
     * <p>
     * 每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于 subLength），按照每两个子链表一组进行合并，
     * 合并后即可得到若干个长度为 subLength×2 的有序子链表（最后一个子链表的长度可以小于 subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做法。
     * <p>
     * 将 subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length，整个链表排序完毕。
     * <p>
     * 如何保证每次合并之后得到的子链表都是有序的呢？可以通过数学归纳法证明。
     * <p>
     * 初始时 subLength=1，每个长度为 1 的子链表都是有序的。
     * <p>
     * 如果每个长度为 subLength 的子链表已经有序，合并两个长度为 subLength 的有序子链表，得到长度为 subLength×2 的子链表，一定也是有序的。
     * <p>
     * 当最后一个子链表的长度小于 subLength 时，该子链表也是有序的，合并两个有序子链表之后得到的子链表一定也是有序的。
     * <p>
     * 因此可以保证最后得到的链表是有序的。
     */
    public static ListNode sortListFBTTMerge(ListNode head) {
        // spec cond
        if (head == null || head.next == null) {
            return head;
        }

        // get list length
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        // dummyHead.next is answer
        ListNode dummyHead = new ListNode(0, head);
        // subLength multiplied by 2 each time
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead,
                     curr = dummyHead.next;
            while (curr != null) {
                // get sublist1 index from 0 to subLength-1
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }

                // get sublist2 index from subLength to length-1
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    // 合并两个链表
    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}

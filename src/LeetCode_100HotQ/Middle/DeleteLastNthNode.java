package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class DeleteLastNthNode {
    // node def
    private static class ListNode {
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

        List<Integer> getList() {
            List<Integer> ret = new ArrayList<>();
            ListNode node = this;
            while (node != null) {
                ret.add(node.val);
                node = node.next;
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = removeFromEnd(head, 5);
        System.out.println(head.getList());
    }

    public static ListNode removeFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();

        ListNode cur = head;
        int pos = 0;
        int length = 0;
        // store position
        // { 0 1 2 3 4 } len5
        while (cur != null) {
            nodeMap.put(length++, cur);
            cur = cur.next;
        }

        // position, length = pos-1, so real pos = length - n + 1 = pos-n
        pos = length - n;
        // delete head
        if (pos == 0) return nodeMap.get(1);
        // delete tail
        else if (pos == length - 1) {
            if (length <= 1) return null;
            else {
                nodeMap.get(pos - 1).next = null;
                return nodeMap.get(0);
            }
        }
        // delete mid
        else {
            nodeMap.get(pos - 1).next = nodeMap.get(pos + 1);
            return nodeMap.get(0);
        }
    }
}

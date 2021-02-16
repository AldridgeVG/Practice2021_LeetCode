package LeetCode_100HotQ.Simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Merge2AscendingList {
    // node def
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
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(5)));
        System.out.println(merge(l1, l2).getList());
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode tmp;
        ListNode ret;
        ListNode pre;
        // spec cond
        if (l1 == null && l2 == null) return null;
        else if (l1 == null) return l2;
        else if (l2 == null) return l1;
        else {

            // l1's first val <= l2's
            if (l1.val > l2.val) {
                tmp = l1;
                l1 = l2;
                l2 = tmp;
            }
            pre = l1;
            ret = l1;
            while (l1 != null && l2 != null) {
                // first time always success
                if (l1.val <= l2.val) {
                    pre = l1;
                    l1 = l1.next;
                }
                // insert in front of l1
                else {
                    tmp = l2;
                    l2 = l2.next;
                    tmp.next = l1;
                    pre.next = tmp;
                    pre = pre.next;
                }
            }
            if (l1 == null)
                pre.next = l2;
            return ret;
        }
    }
}

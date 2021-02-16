package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKOrderedList {
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
        ListNode[] data = new ListNode[4];
        data[0] = new ListNode(1, new ListNode(2, new ListNode(3)));
        data[1] = new ListNode(1, new ListNode(1, new ListNode(4)));
        data[2] = new ListNode(2, new ListNode(2, new ListNode(3)));
        data[3] = new ListNode(0, new ListNode(1, new ListNode(4, new ListNode(5))));
        System.out.println(mergeKLists(data).getList());
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode ret;
        // spec cond
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        // merge one by one
        ret = merge2Lists(lists[0], lists[1]);
        for (int i = 2; i < lists.length; i++) {
            ret = merge2Lists(ret, lists[i]);
        }
        return ret;
    }

    public static ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode tmp, ret;
        ListNode pre = new ListNode();

        // spec cond
        if(l1== null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        //make sure l1's first val <= l2's
        if (l1.val > l2.val) {
            tmp = l1;
            l1 = l2;
            l2 = tmp;
        }
        ret = l1;

        // merge
        while(l1!=null && l2!=null){
            if(l1.val <= l2.val){
                pre = l1;
                l1 = l1.next;
            }
            else{
                tmp = l2;
                l2 = l2.next;
                tmp.next = l1;
                pre.next = tmp;
                pre = tmp;
            }
        }
        if(l1 == null){
            pre.next = l2;
        }
        return ret;
    }
}

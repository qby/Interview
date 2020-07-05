package com.qibenyu.algorithm;

import com.qibenyu.algorithm.LinkedSorted.ListNode;

public class PartitionListLcci {

    public ListNode partition(ListNode head, int x) {

        ListNode dummy = new ListNode(-1);
        ListNode split = dummy;

        ListNode preNode = new ListNode(-1);// = head;

        preNode.next = head;

        ListNode result = preNode;


        while (preNode.next != null) {

            if (preNode.next.val >= x) {
                dummy.next = preNode.next;

                preNode.next = preNode.next.next;

                dummy.next.next = null;

                dummy = dummy.next;
            } else {
                preNode = preNode.next;
            }
        }

        preNode.next = split.next;

        return result.next;

    }

    public static void main(String[] args) {

//        ListNode l6 = new ListNode(2, null);
//        ListNode l5 = new ListNode(5, l6);
//        ListNode l4 = new ListNode(2, l5);
//        ListNode l3 = new ListNode(3, l4);
//        ListNode l2 = new ListNode(4, l3);
        ListNode l1 = new ListNode(1, null);
        ListNode l4 = new ListNode(1, l1);

        ListNode partition = new PartitionListLcci().partition(l4, 0);

        while (partition != null) {

            System.out.println(partition.val);

            partition = partition.next;
        }

    }
}

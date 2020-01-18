package com.qibenyu.algorithm;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class LinkedSorted implements IAlgorithm {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    @NotNull
    @Override
    public String problem() {
        return "链表排序 时间复杂度O(log n)";
    }

    @NotNull
    @Override
    public String condition() {
        return null;
    }

    @NotNull
    @Override
    public String thought() {
        return "归并排序";
    }

    @NotNull
    @Override
    public String answer() {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(1);
//        ListNode node4 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
        ListNode node = sortList(node1);
        return node.toString();
    }

    public ListNode sortList(ListNode head) {

        if (head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;

        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = null;
        ListNode tail = null;
        while (left != null && right != null) {
            if (left.val < right.val) {
                if (head == null) {
                    head = left;
                    tail = left;
                } else {
                    tail.next = left;
                    tail = tail.next;
                }
                left = left.next;
            } else {
                if (head == null) {
                    head = right;
                    tail = right;
                } else {
                    tail.next = right;
                    tail = tail.next;
                }
                right = right.next;
            }
        }

        if (left != null) {
            tail.next = left;
        }

        if (right != null) {
            tail.next = right;
        }

        return head;
    }
}

package com.qibenyu.algorithm;

public class ReverseListList {


    public Node reverseList(Node node) {

        Node pre = null;
        while (node != null) {

            Node next = node.getNext();

            node.setNext(pre);

            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {

        Node node7 = new Node(1, null);
        Node node2 = new Node(2, node7);
        Node node1 = new Node(3, node2);

        Node n = new ReverseListList().reverseList(node1);

        System.out.println(n);

    }
}

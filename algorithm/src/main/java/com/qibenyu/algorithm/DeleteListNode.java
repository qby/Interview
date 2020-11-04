package com.qibenyu.algorithm;

class DeleteListNode {

    public void delete(Node node) {

        if (node == null || node.getNext() == null) return;

        Node first = node;
        Node last = node;

        int index = 2;
        while (index >= 0) {
            if(last.getNext() == null) {
                throw new IllegalArgumentException("小于index数量");
            } else {
                last = last.getNext();
                index--;
            }
        }

        while (last.getNext() != null) {
            last = last.getNext();
            first = first.getNext();
        }

        first.setNext(first.getNext().getNext());
    }

    public static void main(String[] args) {


    }
}

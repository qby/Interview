package com.qibenyu.algorithm;

import android.util.Log;

import java.util.HashMap;

public class LRUCache {

    private HashMap<String, Node> map;

    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {

        this.capacity = capacity;

        map = new HashMap<>();

        head = new Node("", "");
        tail = new Node("", "");

        head.next = tail;
        tail.pre = head;
    }

    private void deleteNode(Node node) {

        Node pre = node.pre;

        pre.next = node.next;
        node.next.pre = pre;
    }

    private void addNode(Node node) {

        Node n = head.next;

        head.next = node;
        node.pre = head;
        node.next = n;
        n.pre = node;
    }

    public String get(String key) {

        if (map.containsKey(key)) {
            Node value = map.get(key);

            if (value == null) return null;

            deleteNode(value);
            addNode(value);
            return value.value;
        }

        return null;
    }

    private static final String TAG = "LRUCache";

    public void put(String key, String value) {

        if (map.containsKey(key)) {
            Node node = map.get(key);
            if (node.value != value) {
                node.value = value;
                map.put(key, node);
                deleteNode(node);
                addNode(node);
            }
        } else {
            if (map.size() >= capacity) {
                Node pre = tail.pre;
                deleteNode(pre);
                System.out.println("put: " + pre.key + " , " + pre.value);
                map.remove(pre.key);
            }
            Node node = new Node(key, value);
            addNode(node);
            map.put(key, node);
        }
    }

    private static class Node {
        Node pre;
        Node next;
        String key;
        String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put("2", "1");
        cache.put("1", "1");
        cache.put("2", "3");    // 该操作会使得密钥 2 作废
        cache.put("4", "1");    // 该操作会使得密钥 1 作废
        String s3 = cache.get("1");// 返回 -1 (未找到)
        String s4 = cache.get("2");// 返回  3

        System.out.println(s3 + " , " + s4);

    }
}

package com.qibenyu.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class RestoreTreeOrder {


    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode restoreOrder(int[] preOrder, int[] inOrder) {
        if (preOrder.length != inOrder.length) {
            return null;
        }

        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return restore(preOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
    }

    private TreeNode restore(int[] preOrder, int pL, int pR, int iL, int iR) {

        if (pL > pR || iL > iR) {
            return null;
        }

        int root = preOrder[pL];

        TreeNode treeNode = new TreeNode(root);

        int index = map.get(root);

        treeNode.left = restore(preOrder, pL + 1, index - iL + pL, iL, index - 1);
        treeNode.right = restore(preOrder, index - iL + pL + 1, pR, index + 1, iR);

        return treeNode;

    }

    public static void main(String[] args) {

        RestoreTreeOrder restoreTreeOrder = new RestoreTreeOrder();
        TreeNode treeNode = restoreTreeOrder.restoreOrder(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode root = queue.poll();
            System.out.println(root.value);

            if (root.left != null)
                queue.add(root.left);
            if (root.right != null)
                queue.add(root.right);
        }

    }

    static class TreeNode {

        public TreeNode(int value) {
            this.value = value;
        }

        int value;
        TreeNode left;
        TreeNode right;
    }
}

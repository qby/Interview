package com.qibenyu.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 通过前序和中序恢复树
 */
public class RestoreTreeOrder {


    HashMap<Integer, Integer> map = new HashMap<>();
    private int[] preOrder;

    public TreeNode restoreOrder(int[] preOrder, int[] inOrder) {
        if (preOrder.length != inOrder.length) {
            return null;
        }
        this.preOrder = preOrder;

        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return restore(0, 0, inOrder.length - 1);
    }

    private TreeNode restore(int pre_root, int iL, int iR) {

        if (iL > iR) {
            return null;
        }

        int root = preOrder[pre_root];

        TreeNode treeNode = new TreeNode(root);

        int index = map.get(root);

        treeNode.left = restore(pre_root + 1, iL, index - 1);
        treeNode.right = restore(index - iL + pre_root + 1, index + 1, iR);

        return treeNode;

    }

    private TreeNode restore(int[] nums, int start, int end) {
        if (start < end) {
            return null;
        }

        TreeNode treeNode = new TreeNode(nums[end]);

        int leftEnd = 0;
        for (int i = start; i < end; i++) {
            if (nums[i] < nums[end]) {
                leftEnd = i;
            }
        }

        treeNode.left = restore(nums, start, leftEnd);
        treeNode.right = restore(nums, leftEnd + 1, end - 1);

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

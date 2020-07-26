package com.qibenyu.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树层序遍历
 */
public class TreeLevelPrint {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> print(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();

        ArrayDeque<TreeNode> deque = new ArrayDeque<>();

        deque.add(root);

        while (!deque.isEmpty()) {

            List<Integer> level = new ArrayList<>();
            int n = deque.size();
            for (int i = 0; i < n; i++) {

                TreeNode poll = deque.remove();

                level.add(poll.val);

                if (poll.left != null) deque.offer(poll.left);
                if (poll.right != null) deque.offer(poll.right);

            }

            lists.add(level);

        }

        return lists;
    }
}

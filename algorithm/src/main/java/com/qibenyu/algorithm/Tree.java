package com.qibenyu.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 中序非递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                list.add(p.val);
                p = p.right;
            }
        }

        return list;
    }

    // 非递归后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;

// 标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        TreeNode pre = p;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {

                stack.push(p);
                p = p.left;

            } else {
                p = stack.pop();

                if (p.right == null || p.right == pre) {
                    res.add(p.val);
//                    pre = cur;
                    p = null;
                } else {
                    stack.push(p);
                    p = p.right;
                    stack.push(p);
                    p = p.left;
                }
            }
        }

        return res;
    }
}

package com.qibenyu.algorithm;

import org.jetbrains.annotations.NotNull;

public class BalanceTree implements IAlgorithm {

    @NotNull
    @Override
    public String problem() {
        return "平衡二叉树";
    }

    @NotNull
    @Override
    public String condition() {
        return null;
    }

    @NotNull
    @Override
    public String thought() {
        return null;
    }

    @NotNull
    @Override
    public String answer() {
        TreeNode node = TreeKt.deserialized("ABC##FX###EG##DY##Z##");
        isBalanceTree(node);
        return String.valueOf(isBalance);
    }

    private boolean isBalance = true;
    public int isBalanceTree(TreeNode node) {

        if (node == null) {
            return 0;
        }
        int left = isBalanceTree(node.getLeft());
        int right= isBalanceTree(node.getRight());

        isBalance = Math.abs(left - right) <= 1 & isBalance;

        return Math.max(left, right) + 1;
    }
}

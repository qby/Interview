package com.qibenyu.algorithm;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 回溯算法   https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
 * 回溯算法   https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
 */
public class Permutations implements IAlgorithm {

    @NotNull
    @Override
    public String problem() {
        return null;
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
        return null;
    }

    List<List<Integer>> result = new LinkedList<>();

    private List<List<Integer>> permute(int[] nums) {

        backtrack(nums, new LinkedList<Integer>());

        return result;

    }

    private void backtrack(int[] nums, LinkedList<Integer> path) {

        if (path.size() == nums.length) {
            result.add(new LinkedList<Integer>(path));
            return;
        }

        for (int num : nums) {
            if (path.contains(num)) {
                continue;
            }

            path.add(num);
            backtrack(nums, path);
            path.removeLast();

        }
    }
}

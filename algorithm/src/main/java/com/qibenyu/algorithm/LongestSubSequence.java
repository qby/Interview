package com.qibenyu.algorithm;

import org.jetbrains.annotations.NotNull;

/**
 * 最长上升子序列
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 *
 *
 *
 *
 * dp[i]最长，dp[i] = dp[i-1] + 1
 */
public class LongestSubSequence implements IAlgorithm{
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
    public String answer() {
        return null;
    }

    @NotNull
    @Override
    public String thought() {
        return null;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int result = 1;
        for (int i = 0; i < nums.length; i++) {

            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }

            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}

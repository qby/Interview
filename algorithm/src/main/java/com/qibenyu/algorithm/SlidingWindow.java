package com.qibenyu.algorithm;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class SlidingWindow implements IAlgorithm {
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

    /**
     * 给定一个整数数组，计算长度为 'k' 的连续子数组的最大总和。
     * 输入：arr [] = {100,200,300,400}
     *      k = 2
     *
     *      输出：700
     * 解释：300 + 400 = 700
     */
    public int maxSum(int[] nums, int k) {
        int max = 0;

        int count = 0;
        for (int i = 0; i < k; i++) {
            count += nums[i];
        }

        max = count;
        for (int i = k; i < nums.length; i++) {
            int a = count - nums[i - k] + nums[i];
            max = Math.max(max, a);
            count = a;
        }

        return max;
    }

    /**
     * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串
     * (minimum-window-substring)
     *
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     *
     * 1. 窗口从0开始，在如果当前字母不包含在T中，left++
     * 2. 包含后，right++ ， 然后right不断自增以找到T中包含的所有字母
     * 3. 为了找到更小的字串，缩小左边left index++ ，判断是否包含全部字母，如果包含，继续缩小左边，如果不包含，增大右边知道包含T中的字母
     * 4. 然后缩小左边
     */
    public String miniWindowSubstring(String s, String t) {

        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //用来统计t中每个字符出现次数
        int[] needs = new int[128];
        //用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];

        for (int i = 0; i < t.length(); i++) {
            needs[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;

        String res = "";

        //目前有多少个字符
        int count = 0;

        //用来记录最短需要多少个字符。
        int minLength = s.length() + 1;

        while (right < s.length()) {
            char ch = s.charAt(right);
            window[ch]++;
            if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                count++;
            }

            //移动到不满足条件为止
            while (count == t.length()) {
                ch = s.charAt(left);
                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    res = s.substring(left, right + 1);

                }
                window[ch]--;
                left++;

            }
            right++;

        }
        return res;

    }


    public int lengthOfLongestSubstring(String s) {


        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args) {

        new SlidingWindow().lengthOfLongestSubstring("tmmzuxt");
    }
}

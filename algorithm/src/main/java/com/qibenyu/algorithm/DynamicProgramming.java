package com.qibenyu.algorithm;

import android.text.TextUtils;

import java.util.Arrays;

/**
 * 动态规划
 * <p>
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * <p>
 * <p>
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * <p>
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * <p>
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 * <p>
 * <p>
 * dp[i] = Math.max(dp[i - 1] ,  dp[n - 2] + nums[i]);
 */
public class DynamicProgramming {

    public int massage(int[] nums) {
        int n = nums.length;
        // 处理边界条件。
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        // 定义dp数组，按照状态转移方程递推。
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];

//        int a = 0, b = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//            int c = Math.max(b, a + nums[i]);
//            a = b;
//            b = c;
//        }
//        return b;
    }

    public String longestPalindrome(String s) {

        if(s== null || s.length() == 0) return "";

        int n = s.length();
        boolean[][] isPalindrom = new boolean[n +1][n + 1];

        String result = "";
        for(int j = 0; j < n+ 1; j++) {
            for(int i = j ; i >=0 ; i--) {
                if(i == j || i + 1 == j) {
                    isPalindrom[i][j] = true;
                } else if(s.charAt(i) == s.charAt(j - 1) && isPalindrom[i + 1][j - 1]) {
                    isPalindrom[i][j] = true;
                }

                if(i != j && isPalindrom[i][j] && result.length() < j - i) {
                    result = s.substring(i,j);
                }
            }
            for (boolean[] isp : isPalindrom) {
                System.out.println(Arrays.toString(isp));
            }
        }

        return result;

    }

    public static void main(String[] args) {
        String d = new DynamicProgramming().longestPalindrome("abbc");

    }


}

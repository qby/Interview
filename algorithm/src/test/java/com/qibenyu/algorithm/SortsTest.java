package com.qibenyu.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortsTest {

    @Test
    public void answer() {

//        Sorts sorts = new Sorts();
//        String result = sorts.answer();
//
//        System.out.println(result);
//        String r = longestCommonPrefix(new String[]{"c","c"});

//        Sorts sorts = new Sorts();
//        sorts.answer();
//        assertEquals("c","c");
        int a = maxProfit(new int[]{2,1,2,1,0,1,2});

        System.out.println("a");
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        }
        if(strs.length == 1) {
            return strs[0];
        }
        int min = Integer.MAX_VALUE;
        String preS = strs[0];
        for(int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int length = Math.min(str.length(), preS.length());
            int start  = 0;
            for(int j = 0; j < length; j++) {

                if(preS.charAt(j) == str.charAt(j)) {
                    start++;
                }
            }
            min = Math.min(min, start);
            preS = str;
        }
        if(min == Integer.MAX_VALUE) {
            min = 0;
        }
        return preS.substring(0,min);
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 1) return 0;
        int result = 0;
        int pre = 0;
        for(int i = 1; i < prices.length; i++ ) {

            int d = prices[i] - prices[pre];
            if(d<0) {
                pre = i;
            } else {
                if(d > result) {
                    result = d;
                }
            }
        }
        return result;
    }
}
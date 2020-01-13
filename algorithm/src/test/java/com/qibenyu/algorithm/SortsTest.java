package com.qibenyu.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortsTest {

    @Test
    public void answer() {
//        String r = longestCommonPrefix(new String[]{"c","c"});

        Sorts sorts = new Sorts();
        sorts.answer();
//        assertEquals("c","c");
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
}
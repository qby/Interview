package com.qibenyu.algorithm;

public class LongestCommonPrefix {

    /**
     *
     *
     * while 如果为0 则找到了这个prefix， 如果为-1 则没有找到，prefix.substring(0,prefix.length() - 1)
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {

            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0,prefix.length() - 1);
                if (prefix.length() == 0) return "";
            }
        }
        return prefix;

    }

    public static void main(String[] args) {

        String s = new LongestCommonPrefix().longestCommonPrefix(new String[]{"flower","flow","flight"});
        System.out.println(s);
    }

}

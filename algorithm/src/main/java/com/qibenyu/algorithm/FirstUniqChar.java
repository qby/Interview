package com.qibenyu.algorithm;

public class FirstUniqChar {


    public char firstUniqChar(String s) {

        if(s.length() == 0) return ' ';

        int[] array = new int[26];

        for(int i = 0; i < s.length() ; i++) {

            array[s.charAt(i) - 'a'] += 1;
        }

        for(int i = 0 ; i < s.length(); i ++) {

            if(array[s.charAt(i) - 'a'] == 1)
                return s.charAt(i);
        }

        return ' ';
    }
}

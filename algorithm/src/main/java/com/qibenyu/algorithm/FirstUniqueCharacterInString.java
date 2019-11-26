package com.qibenyu.algorithm;


import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 */
public class FirstUniqueCharacterInString implements IAlgorithm {

    private static final String TAG = "FirstUniqueCharacterInS";
    @Override
    public String problem() {
        return "给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。";
    }

    @Override
    public String condition() {
        return "loveleetcode";
    }

    @Override
    public String thought() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int findString(String s) {
//        Map<Character, Integer> map = new HashMap<>();
        char[] chars = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
//            map.put(c, map.getOrDefault(c, 0) + 1);
            chars[c] += 1;
        }

        for (int i = 0; i < s.length(); i++) {

//            int count = map.get(s.charAt(i));
            int c = s.charAt(i);
            Log.d(TAG, "findString: count = " + chars[c]);
            if (chars[c]== 1) {
                return i;
            }
        }
        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String answer() {
        int i = findString("loveleetcode");
        return String.valueOf(i);
    }

}

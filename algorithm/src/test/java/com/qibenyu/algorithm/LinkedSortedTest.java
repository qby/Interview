package com.qibenyu.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;

public class LinkedSortedTest {

    @Test
    public void answer() {
        LinkedSorted sorted = new LinkedSorted();
        sorted.answer();
    }

    private HashMap<Character, Character> map;

    {
        map = new HashMap();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            } else{
                char left = stack.empty() ? '#' : stack.pop();//(
                Character ch;
                if ((ch = map.get(left)) == null || ch != map.get(left)) {// c = )
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private void a() {
        ArrayList list = new ArrayList();

    }
}
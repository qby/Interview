package com.qibenyu.algorithm;

public class StringToInt {

    public static int parseInt(String s) {

        if (s == null || s.length() == 0) return 0;

        int index = 0;
        long result = 0;

        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        if (s.length() == index) return 0;

        boolean negetive = false;
        if (s.charAt(index) == '-') {
            negetive = true;
            index++;
        } else if (s.charAt(index) == '+') {
            index++;
        }

        while (index < s.length()) {
            char c = s.charAt(index);
            if (c > '9' || c < '0') throw new IllegalArgumentException();

            result = result * 10 + c - '0';

            if (result > Integer.MAX_VALUE) {
                return negetive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }

        return negetive ? (int) result : (int) -result;
    }

    public static void main(String[] args) {

    }
}

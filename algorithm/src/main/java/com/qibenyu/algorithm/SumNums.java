package com.qibenyu.algorithm;

public class SumNums {

    public int sumNums(int n) {
        int result = 0;
        boolean is = n == 0 || (result = sumNums(n - 1) + n) > 0;
        return result;
    }
}

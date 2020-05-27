package com.qibenyu.algorithm;

import java.util.concurrent.Executors;

public class BinarySqrt {

    public double sqrt(double n) {

        double max,mid,min;
        max = n;
        min = 0;

        mid = (min + max) / 2;

        while(true) {
            if (n - mid * mid < 0.001) break;
            mid = (min + max) / 2;

            if (mid * mid > n) {
                max = n;
            } else if(mid * mid < n) {
                min = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }
}

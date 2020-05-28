package com.qibenyu.algorithm;

import java.util.concurrent.Executors;

public class BinarySqrt {

    public double sqrt(double n) {

        double left = 0;
        double right = n;
        double result = (left + right) / 2;


        while (Math.abs(n - result * result) > 0.01) {

            if (result * result > n) {
                right = result;
            } else if (result * result < n) {
                left = result;
            } else {
                return result;
            }
            result = (left + right) / 2;
        }

        return result;
    }

    public static void main(String[] args) {

        BinarySqrt s = new BinarySqrt();
        double re = s.sqrt(16);
        System.out.println(re);
    }
}

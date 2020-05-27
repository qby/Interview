package com.qibenyu.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlidingWindowTest {

    @Test
    public void maxSum() {
        SlidingWindow sw = new SlidingWindow();
        int i = sw.maxSum(new int[]{5,2,3,1,5}, 2);

        assertEquals(7, i);

    }
}